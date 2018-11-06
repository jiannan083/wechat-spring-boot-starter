package cn.wangjiannan.wechat.model.message;

/**
 * 回复消息-文本消息.
 *
 * @author wangjiannan
 */
public class ResponseTextMessage extends ResponseBaseMessage {
    /**
     * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）.
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @Override
    public String toString() {
        return "ResponseTextMessage{" +
                "Content='" + Content + '\'' +
                '}';
    }
}
