package cn.wangjiannan.wechat.model.menu;

import lombok.Data;

/**
 * 按钮.
 */
@Data
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
}
