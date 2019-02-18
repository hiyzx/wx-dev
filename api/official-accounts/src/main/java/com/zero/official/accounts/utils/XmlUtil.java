package com.zero.official.accounts.utils;

import com.thoughtworks.xstream.XStream;
import com.zero.official.accounts.vo.wx.XmlMessageSend;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlUtil {

    /**
     * 接收到的XML格式，转化为Map对象
     */
    public static Map<String, Object> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
        Map<String, Object> map = new HashMap<>();
        // 从dom4j的jar包中，拿到SAXReader对象。
        SAXReader reader = new SAXReader();
        InputStream is = request.getInputStream();// 从request中，获取输入流
        Document doc = reader.read(is);// 从reader对象中,读取输入流
        Element root = doc.getRootElement();// 获取XML文档的根元素
        List<Element> list = root.elements();// 获得根元素下的所有子节点
        for (Element e : list) {
            map.put(e.getName(), e.getText());// 遍历list对象，并将结果保存到集合中
        }
        is.close();
        return map;
    }

    /**
     * 将文本消息对象转化成XML格式
     */
    public static String objectToXml(XmlMessageSend message) {
        XStream xs = new XStream();
        // 由于转换后xml根节点默认为class类，需转化为
        xs.alias("xml", message.getClass());
        return xs.toXML(message);
    }
}