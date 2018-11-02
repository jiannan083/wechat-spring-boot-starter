package cn.wangjiannan.wechat.model;

import lombok.Data;

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
    private String toUserName;
    /**
     * 开发者微信号.
     */
    private String fromUserName;
    /**
     * 消息创建时间 （整型）.
     */
    private Long createTime;
    /**
     * 消息类型（text/music/news/image）.
     */
    private String msgType;
}
