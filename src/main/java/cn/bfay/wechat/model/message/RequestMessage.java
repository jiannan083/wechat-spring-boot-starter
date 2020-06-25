package cn.bfay.lion.wechat.model.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 请求消息基本类.
 *
 * @author wangjiannan
 */
@Data
public class RequestMessage {
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
}
