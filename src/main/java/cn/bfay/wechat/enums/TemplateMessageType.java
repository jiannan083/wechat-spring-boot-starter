package cn.bfay.wechat.enums;

/**
 * 模板消息类型.
 *
 * @author wangjiannan
 */
public enum TemplateMessageType {

    TEMPLATE_MESSAGE_1(1, "B2S7Ib4-VNZF4rrhn5OEeaQwM2C163v8XWq0TMnu6sM", "优惠通知");


    private int key;
    private String value;
    private String name;

    TemplateMessageType() {
    }

    TemplateMessageType(int key, String value, String name) {
        this.key = key;
        this.value = value;
        this.name = name;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
