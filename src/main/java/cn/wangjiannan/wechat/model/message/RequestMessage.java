package cn.wangjiannan.wechat.model.message;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 请求消息基本类.
 *
 * @author wangjiannan
 */
public class RequestMessage {
    /**
     * 开发者微信号.
     */
    @JSONField(name = "ToUserName")
    private String toUserName;
    /**
     * 发送方帐号（一个OpenID）.
     */
    @JSONField(name = "FromUserName")
    private String fromUserName;
    /**
     * 消息创建时间 （整型）.
     */
    @JSONField(name = "CreateTime")
    private String createTime;
    /**
     * 消息类型（text/image/location/link）.
     */
    @JSONField(name = "MsgType")
    private String msgType;
    /**
     * 消息id，64位整型.
     */
    @JSONField(name = "MsgId")
    private String msgId;

    /**
     * 文本消息内容.
     */
    @JSONField(name = "Content")
    private String content;

    /**
     * 图片链接（由系统生成）.
     */
    @JSONField(name = "PicUrl")
    private String picUrl;
    /**
     * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据;
     * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据;
     * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据.
     */
    @JSONField(name = "MediaId")
    private String mediaId;
    /**
     * 语音格式，如amr，speex等.
     */
    @JSONField(name = "Format")
    private String format;
    /**
     * 语音识别结果，UTF8编码.
     */
    @JSONField(name = "Recognition")
    private String recognition;
    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据.
     */
    @JSONField(name = "ThumbMediaId")
    private String thumbMediaId;

    /**
     * 地理位置维度.
     */
    @JSONField(name = "Location_X")
    private String locationX;
    /**
     * 地理位置经度.
     */
    @JSONField(name = "Location_Y")
    private String locationY;
    /**
     * 地图缩放大小.
     */
    @JSONField(name = "Scale")
    private String scale;
    /**
     * 地理位置信息.
     */
    @JSONField(name = "Label")
    private String label;

    /**
     * 消息标题.
     */
    @JSONField(name = "Title")
    private String title;
    /**
     * 消息描述.
     */
    @JSONField(name = "Description")
    private String description;
    /**
     * 消息链接.
     */
    @JSONField(name = "Url")
    private String url;

    /**
     * 接收事件推送.
     */
    @JSONField(name = "Event")
    private String event;

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
                '}';
    }
}
