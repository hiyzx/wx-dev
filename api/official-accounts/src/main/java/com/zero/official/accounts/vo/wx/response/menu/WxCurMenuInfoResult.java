package com.zero.official.accounts.vo.wx.response.menu;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WxCurMenuInfoResult {
    private int is_menu_open;
    private WxCurSelfMenuInfo selfmenu_info;

    @Data
    public static class WxCurSelfMenuInfo {
        private List<WxCurMenuButtonInfo> button = new ArrayList<>();
    }

    @Data
    public static class WxCurMenuButtonInfo {
        private String type;
        private String name;
        private String key;
        private String url;
        private String value;
        private WxCurMenuNews news_info;
        private WxCurMenuButton sub_button;
    }

    @Data
    public static class WxCurMenuButton {
        private List<WxCurMenuButtonInfo> list = new ArrayList<WxCurMenuButtonInfo>();
    }

    @Data
    public static class WxCurMenuNews {
        private List<WxCurMenuNewsInfo> list = new ArrayList<>();
    }

    @Data
    public static class WxCurMenuNewsInfo {
        private String title;// 图文消息的标题
        private String author;// 作者
        private String digest;// 摘要
        private int show_cover;// 是否显示封面，0为不显示，1为显示
        private String cover_url;// 封面图片的URL
        private String content_url;// 正文的URL
        private String source_url;// 原文的URL，若置空则无查看原文入口
    }

}
