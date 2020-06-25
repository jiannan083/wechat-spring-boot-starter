package cn.bfay.wechat.model.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 回复消息-图文消息的Articles.
 *
 * @author wangjiannan
 */
public class Article implements Serializable {
    private static final long serialVersionUID = 6632853642268728873L;

    /**
     * 图文消息名称.
     */
    @JsonProperty("Title")
    private String title;
    /**
     * 图文消息描述.
     */
    @JsonProperty("Description")
    private String description;
    /**
     * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，
     * 小图200*200，限制图片链接的域名需要与开发者填写的基本资料中的Url一致.
     */
    @JsonProperty("PicUrl")
    private String picUrl;
    /**
     * 点击图文消息跳转链接.
     */
    @JsonProperty("Url")
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
