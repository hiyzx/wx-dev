package com.zero.official.accounts.vo.wx;

import lombok.Data;

@Data
public class WxAccessToken {

    private String access_token;
    private int expires_in = -1;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
