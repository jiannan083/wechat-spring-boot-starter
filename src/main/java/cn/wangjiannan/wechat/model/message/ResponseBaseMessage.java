package cn.wangjiannan.wechat.model.message;

/**
 * 回复消息基本类.
 *
 * @author wangjiannan
 */
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

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    @Override
    public String toString() {
        return "ResponseBaseMessage{" +
                "ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", CreateTime=" + CreateTime +
                ", MsgType='" + MsgType + '\'' +
                '}';
    }
}
