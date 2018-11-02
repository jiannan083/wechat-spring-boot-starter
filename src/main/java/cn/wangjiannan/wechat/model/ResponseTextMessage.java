package cn.wangjiannan.wechat.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 回复消息-文本消息.
 *
 * @author wangjiannan
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResponseTextMessage extends ResponseBaseMessage {
    /**
     * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）.
     */
    private String content;
}
