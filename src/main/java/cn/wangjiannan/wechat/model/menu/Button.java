package cn.wangjiannan.wechat.model.menu;

import java.util.Arrays;

/**
 * 一级菜单.
 */
public class Button {
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
     * media_id类型和view_limited类型必须,调用新增永久素材接口返回的合法media_id.
     */
    private String media_id;
    /**
     * 二级菜单数组，个数应为1~5个.
     */
    private SubButton[] sub_button;

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

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public SubButton[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(SubButton[] sub_button) {
        this.sub_button = sub_button;
    }

    @Override
    public String toString() {
        return "Button{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", key='" + key + '\'' +
                ", media_id='" + media_id + '\'' +
                ", sub_button=" + Arrays.toString(sub_button) +
                '}';
    }
}
