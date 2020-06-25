package cn.bfay.lion.wechat.model.message;

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
    private String ToUserName;
    /**
     * 开发者微信号.
     */
    private String FromUserName;
    /**
     * 消息创建时间 （整型）.
     */
    private Long CreateTime;
    /**
     * 消息类型（text/music/news/image）.
     */
    private String MsgType;
}
