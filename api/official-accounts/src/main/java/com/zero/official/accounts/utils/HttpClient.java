package com.zero.official.accounts.utils;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.*;
import java.util.Map.Entry;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HTTP连接池
 *
 * @author yezhaoxing
 * @date 2017/7/18
 * @see https://yq.aliyun.com/articles/294
 */
public class HttpClient {
    private static final Logger LOG = LoggerFactory.getLogger(HttpClient.class);
    private static final String CONTENT_TYPE = "content-type";
    private static final String APPLICATION_JSON = "application/json";
    private static final int TIME_OUT = 30 * 1000;
    private static final String UTF_8 = "UTF-8";
    private final RequestConfig requestConfig;
    private final String scheme;
    private final String hostname;
    private final int port;
    private CloseableHttpClient httpClient = null;

    public HttpClient(String scheme, String hostname, int port) {
        this.scheme = scheme;
        this.hostname = hostname;
        this.port = port;
        // 配置请求的超时设置
        requestConfig = RequestConfig.custom().setConnectionRequestTimeout(TIME_OUT * 3).setConnectTimeout(TIME_OUT * 2)
                .setSocketTimeout(TIME_OUT * 2).build();
        httpClient = createHttpClient(100, 100, 1, hostname, port);
    }

    private static CloseableHttpClient createHttpClient(int maxTotal, int maxPerRoute, int maxRoute, String hostname,
            int port) {
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("http", plainsf).register("https", sslsf).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        // 将最大连接数增加
        cm.setMaxTotal(maxTotal);
        // 将每个路由基础的连接增加
        cm.setDefaultMaxPerRoute(maxPerRoute);
        HttpHost httpHost = new HttpHost(hostname, port);
        // 将目标主机的最大连接数增加
        cm.setMaxPerRoute(new HttpRoute(httpHost), maxRoute);

        // 请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = (exception, executionCount, context) -> {
            if (executionCount >= 2) {// 如果已经重试了2次，就放弃
                return false;
            }
            if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                return true;
            }
            if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                return false;
            }
            if (exception instanceof InterruptedIOException) {// 超时
                return false;
            }
            if (exception instanceof UnknownHostException) {// 目标服务器不可达
                return false;
            }
            if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
                return false;
            }
            if (exception instanceof SSLException) {// SSL握手异常
                return false;
            }

            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            // 如果请求是幂等的，就再次尝试
            if (!(request instanceof HttpEntityEnclosingRequest)) {
                return true;
            }
            return false;
        };
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm)
                .setRetryHandler(httpRequestRetryHandler).build();
        return httpClient;
    }

    public String post(String path) throws IOException {
        return post(path, Collections.emptyMap(), Collections.emptyMap());
    }

    public String post(String path, Map<String, String> params) throws IOException {
        return post(path, params, Collections.emptyMap());
    }

    public String post(String path, Map<String, String> params, Map<String, String> headers) throws IOException {
        CloseableHttpResponse response = null;
        String rtn = null;
        try {
            URI uri = createURIBuilder(path);
            HttpPost httppost = new HttpPost(uri);

            List<NameValuePair> nameValuePairs = new ArrayList<>();
            for (Entry<String, String> paramEntry : params.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(paramEntry.getKey(), paramEntry.getValue()));
            }
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, UTF_8));
            if (headers != null && !headers.isEmpty()) {
                for (Entry<String, String> headerEntry : headers.entrySet()) {
                    httppost.addHeader(headerEntry.getKey(), headerEntry.getValue());
                }
            }
            httppost.setConfig(requestConfig);
            response = httpClient.execute(httppost, HttpClientContext.create());
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, UTF_8);
            EntityUtils.consume(entity);
            rtn = result;
        } catch (IOException e) {
            throw e;
        } catch (URISyntaxException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return rtn;
    }

    public String post(String path, String requestBody) throws IOException {
        LOG.debug("{}", requestBody);
        CloseableHttpResponse response = null;
        String rtn = null;
        try {
            URI uri = createURIBuilder(path);
            HttpPost httppost = new HttpPost(uri);
            httppost.setEntity(new StringEntity(requestBody, UTF_8));
            httppost.addHeader(CONTENT_TYPE, APPLICATION_JSON);
            httppost.setConfig(requestConfig);
            response = httpClient.execute(httppost, HttpClientContext.create());
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, UTF_8);
            EntityUtils.consume(entity);
            rtn = result;
        } catch (URISyntaxException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return rtn;
    }

    public String put(String path, String requestBody) throws IOException {
        LOG.debug("{}", requestBody);
        CloseableHttpResponse response = null;
        String rtn = null;
        try {
            URI uri = createURIBuilder(path);
            HttpPut httpput = new HttpPut(uri);
            httpput.setEntity(new StringEntity(requestBody, UTF_8));
            httpput.addHeader(CONTENT_TYPE, APPLICATION_JSON);
            httpput.setConfig(requestConfig);
            response = httpClient.execute(httpput, HttpClientContext.create());
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, UTF_8);
            EntityUtils.consume(entity);
            rtn = result;
        } catch (URISyntaxException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return rtn;
    }

    public String get(String path) {
        return get(path, Collections.emptyMap());
    }

    public String get(String path, Map<String, String> headers) {
        CloseableHttpResponse response = null;
        String rtn = null;
        try {
            URI uri = createURIBuilder(path);
            HttpGet httpget = new HttpGet(uri);
            httpget.setConfig(requestConfig);
            if (headers != null && !headers.isEmpty()) {
                for (Entry<String, String> headerEntry : headers.entrySet()) {
                    httpget.addHeader(headerEntry.getKey(), headerEntry.getValue());
                }
            }
            response = httpClient.execute(httpget, HttpClientContext.create());
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, UTF_8);
            EntityUtils.consume(entity);
            rtn = result;
        } catch (IOException | URISyntaxException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return rtn;
    }

    private URI createURIBuilder(String path) throws URISyntaxException {
        String[] urlParts = path.split("\\?");
        URIBuilder builder = new URIBuilder().setScheme(scheme).setHost(hostname).setPort(port).setPath(urlParts[0]);
        if (urlParts.length > 1) {
            for (Entry<String, String> entry : getParamsByURI(urlParts[1]).entrySet()) {
                builder.addParameter(entry.getKey(), entry.getValue());
            }
        }
        return builder.build();
    }

    private Map<String, String> getParamsByURI(String query) {
        Map<String, String> params = new HashMap<>();
        for (String param : query.split("&")) {
            try {
                String[] pair = param.split("=");
                String key = URLDecoder.decode(pair[0], "UTF-8");
                String value = "";
                if (pair.length > 1) {
                    value = URLDecoder.decode(pair[1], "UTF-8");
                }
                params.put(key, value);
            } catch (UnsupportedEncodingException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return params;
    }
}
