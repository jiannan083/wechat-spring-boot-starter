package cn.bfay.wechat.model.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 回复消息-文本消息.
 *
 * @author wangjiannan
 */
public class ResponseTextMessage extends ResponseBaseMessage {
    private static final long serialVersionUID = 1818157417491144968L;

    /**
     * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）.
     */
    @JsonProperty("Content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ResponseTextMessage{" +
                "content='" + content + '\'' +
                '}';
    }
}
