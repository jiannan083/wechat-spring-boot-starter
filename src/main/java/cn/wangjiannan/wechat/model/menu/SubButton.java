package cn.wangjiannan.wechat.model.menu;

import lombok.Data;

/**
 * 子按钮.
 */
@Data
public class SubButton {
    /**
     * 菜单标题，不超过16个字节，子菜单不超过60个字节.
     */
    private String name;
    /**
     * 菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型.
     */
    private String type;
    /**
     * click等点击类型必须,菜单KEY值，用于消息接口推送，不超过128字节..
     */
    private String key;
    /**
     * view、miniprogram类型必须,网页 链接，用户点击菜单可打开链接，不超过1024字节。 type为miniprogram时，不支持小程序的老版本客户端将打开本url.
     */
    private String url;
    /**
     * miniprogram类型必须,小程序的appid（仅认证公众号可配置）.
     */
    private String appid;
    /**
     * miniprogram类型必须,小程序的页面路径.
     */
    private String pagepath;
}
