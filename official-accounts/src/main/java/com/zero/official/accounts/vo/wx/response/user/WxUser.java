package com.zero.official.accounts.vo.wx.response.user;

import lombok.Data;

@Data
public class WxUser {
        private int subscribe;
        private String openid;
        private String nickname;
        private int sex;
        private String language;
        private String city;
        private String province;
        private String country;
        private String headimgurl;
        private String subscribe_time;
        private String unionid;
        private String remark;
        private int groupid;
        private String[] tagid_list;
        private String[] privilege;
        private String subscribe_scene;
        private int qr_scene;
        private String qr_scene_str;
}
