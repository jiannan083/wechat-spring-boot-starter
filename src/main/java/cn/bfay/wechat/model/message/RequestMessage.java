package cn.bfay.wechat.model.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 请求消息基本类.
 *
 * @author wangjiannan
 */
public class RequestMessage implements Serializable {
    private static final long serialVersionUID = 235103907027992856L;

    /**
     * 开发者微信号.
     */
    @JsonProperty("ToUserName")
    private String toUserName;
    /**
     * 发送方帐号（一个OpenID）.
     */
    @JsonProperty("FromUserName")
    private String fromUserName;
    /**
     * 消息创建时间 （整型）.
     */
    @JsonProperty("CreateTime")
    private String createTime;
    /**
     * 消息类型（text/image/location/link）.
     */
    @JsonProperty("MsgType")
    private String msgType;
    /**
     * 消息id，64位整型.
     */
    @JsonProperty("MsgId")
    private String msgId;

    /**
     * 文本消息内容.
     */
    @JsonProperty("Content")
    private String content;

    /**
     * 图片链接（由系统生成）.
     */
    @JsonProperty("PicUrl")
    private String picUrl;
    /**
     * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据;
     * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据;
     * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据.
     */
    @JsonProperty("MediaId")
    private String mediaId;
    /**
     * 语音格式，如amr，speex等.
     */
    @JsonProperty("Format")
    private String format;
    /**
     * 语音识别结果，UTF8编码.
     */
    @JsonProperty("Recognition")
    private String recognition;
    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据.
     */
    @JsonProperty("ThumbMediaId")
    private String thumbMediaId;

    /**
     * 地理位置维度.
     */
    @JsonProperty("Location_X")
    private String locationX;
    /**
     * 地理位置经度.
     */
    @JsonProperty("Location_Y")
    private String locationY;
    /**
     * 地图缩放大小.
     */
    @JsonProperty("Scale")
    private String scale;
    /**
     * 地理位置信息.
     */
    @JsonProperty("Label")
    private String label;

    /**
     * 消息标题.
     */
    @JsonProperty("Title")
    private String title;
    /**
     * 消息描述.
     */
    @JsonProperty("Description")
    private String description;
    /**
     * 消息链接.
     */
    @JsonProperty("Url")
    private String url;

    /**
     * 接收事件推送.
     */
    @JsonProperty("Event")
    private String event;

    /**
     * 事件KEY值，与创建自定义菜单时指定的KEY值对应.
     */
    @JsonProperty("EventKey")
    private String eventKey;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    @Override
    public String toString() {
        return "RequestMessage{" +
                "toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", msgType='" + msgType + '\'' +
                ", msgId='" + msgId + '\'' +
                ", content='" + content + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", mediaId='" + mediaId + '\'' +
                ", format='" + format + '\'' +
                ", recognition='" + recognition + '\'' +
                ", thumbMediaId='" + thumbMediaId + '\'' +
                ", locationX='" + locationX + '\'' +
                ", locationY='" + locationY + '\'' +
                ", scale='" + scale + '\'' +
                ", label='" + label + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", event='" + event + '\'' +
                ", eventKey='" + eventKey + '\'' +
                '}';
    }
}
