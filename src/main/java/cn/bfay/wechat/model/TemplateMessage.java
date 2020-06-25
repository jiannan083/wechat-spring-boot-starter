package cn.bfay.lion.wechat.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 模板消息实体.
 *
 * @author wangjiannan
 */
@Data
public class TemplateMessage {
    /**
     * 是	接收者openid.
     */
    private String touser;
    /**
     * 是	模板ID.
     */
    @JsonProperty("template_id")
    private String templateId;
    /**
     * 否 模板跳转链接（海外帐号没有跳转能力）.
     */
    private String url;
    ///**
    // * 否 跳小程序所需数据，不需跳小程序可不用传该数据.
    // */
    //private String miniprogram;
    ///**
    // * 是 所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系，暂不支持小游戏）.
    // */
    //private String appid;
    ///**
    // * 否 所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），暂不支持小游戏.
    // */
    //private String pagepath;
    /**
     * 是 模板数据.
     */
    private Object data;
    ///**
    // * 否 模板内容字体颜色，不填默认为黑色.
    // */
    //private String color;
}
