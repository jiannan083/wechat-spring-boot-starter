package cn.bfay.wechat.model;

/**
 * 模板消息实体.
 *
 * @author wangjiannan
 */
public class TemplateMessage {
    /**
     * 是	接收者openid.
     */
    private String touser;
    /**
     * 是	模板ID.
     */
    private String templateId;
    /**
     * 否 模板跳转链接（海外帐号没有跳转能力）.
     */
    private String url;
    ///**
    // * 否 跳小程序所需数据，不需跳小程序可不用传该数据.
    // */
    //private String miniprogram;
    ///**
    // * 是 所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系，暂不支持小游戏）.
    // */
    //private String appid;
    ///**
    // * 否 所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），暂不支持小游戏.
    // */
    //private String pagepath;
    /**
     * 是 模板数据.
     */
    private String data;
    ///**
    // * 否 模板内容字体颜色，不填默认为黑色.
    // */
    //private String color;


    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
