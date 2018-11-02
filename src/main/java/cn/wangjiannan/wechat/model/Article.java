package cn.wangjiannan.wechat.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

/**
 * 回复消息-图文消息的Articles.
 *
 * @author wangjiannan
 */
@Data
public class Article {
    /**
     * 图文消息名称.
     */
    @XmlElement(name = "Title")
    private String title;
    /**
     * 图文消息描述.
     */
    @XmlElement(name = "Description")
    private String description;
    /**
     * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，
     * 小图200*200，限制图片链接的域名需要与开发者填写的基本资料中的Url一致.
     */
    @XmlElement(name = "PicUrl")
    private String picUrl;
    /**
     * 点击图文消息跳转链接.
     */
    @XmlElement(name = "Url")
    private String url;
}
