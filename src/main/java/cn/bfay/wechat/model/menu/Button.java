package cn.bfay.wechat.model.menu;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 一级菜单.
 */
public class Button implements Serializable {
    private static final long serialVersionUID = -6095670386244383618L;

    /**
     * 菜单标题，不超过16个字节，子菜单不超过60个字节.
     */
    private String name;
    /**
     * 菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型.
     */
    private String type;
    /**
     * click等点击类型必须,菜单KEY值，用于消息接口推送，不超过128字节.
     */
    private String key;
    /**
     * view、miniprogram类型必须.
     */
    private String url;
    /**
     * media_id类型和view_limited类型必须,调用新增永久素材接口返回的合法media_id.
     */
    @JsonProperty("media_id")
    private String mediaId;
    /**
     * 二级菜单数组，个数应为1~5个.
     */
    @JsonProperty("sub_button")
    private SubButton[] subButton;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public SubButton[] getSubButton() {
        return subButton;
    }

    public void setSubButton(SubButton[] subButton) {
        this.subButton = subButton;
    }

    @Override
    public String toString() {
        return "Button{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", key='" + key + '\'' +
                ", url='" + url + '\'' +
                ", mediaId='" + mediaId + '\'' +
                ", subButton=" + Arrays.toString(subButton) +
                '}';
    }
}
