package cn.bfay.wechat.model.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 回复消息基本类.
 *
 * @author wangjiannan
 */
public class ResponseBaseMessage implements Serializable {
    private static final long serialVersionUID = -25366556272047256L;

    /**
     * 接收方帐号（收到的OpenID）.
     */
    @JsonProperty("ToUserName")
    private String toUserName;
    /**
     * 开发者微信号.
     */
    @JsonProperty("FromUserName")
    private String fromUserName;
    /**
     * 消息创建时间 （整型）.
     */
    @JsonProperty("CreateTime")
    private Long createTime;
    /**
     * 消息类型（text/music/news/image）.
     */
    @JsonProperty("MsgType")
    private String msgType;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @Override
    public String toString() {
        return "ResponseBaseMessage{" +
                "toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime=" + createTime +
                ", msgType='" + msgType + '\'' +
                '}';
    }
}
