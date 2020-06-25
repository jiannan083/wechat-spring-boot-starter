package cn.bfay.lion.wechat.model.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 回复消息-图文消息.
 *
 * @author wangjiannan
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseNewsMessage extends ResponseBaseMessage {
    /**
     * 图文消息个数，限制为10条以内.
     */
    private int ArticleCount;
    /**
     * 多条图文消息信息，默认第一个item为大图.
     */
    private List<Article> Articles;
}
