package cn.wangjiannan.wechat.model.menu;

/**
 * 二级菜单.
 */
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }

    @Override
    public String toString() {
        return "SubButton{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", key='" + key + '\'' +
                ", url='" + url + '\'' +
                ", appid='" + appid + '\'' +
                ", pagepath='" + pagepath + '\'' +
                '}';
    }
}
