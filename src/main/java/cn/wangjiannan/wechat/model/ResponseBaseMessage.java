package cn.wangjiannan.wechat.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

/**
 * 回复消息基本类.
 *
 * @author wangjiannan
 */
@Data
public class ResponseBaseMessage {
    /**
     * 接收方帐号（收到的OpenID）.
     */
    @XmlElement(name = "ToUserName")
    private String toUserName;
    /**
     * 开发者微信号.
     */
    @XmlElement(name = "FromUserName")
    private String fromUserName;
    /**
     * 消息创建时间 （整型）.
     */
    @XmlElement(name = "CreateTime")
    private Long createTime;
    /**
     * 消息类型（text/music/news/image）.
     */
    @XmlElement(name = "MsgType")
    private String msgType;
}
