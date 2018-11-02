package cn.wangjiannan.wechat.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * 回复消息-图文消息.
 *
 * @author wangjiannan
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResponseNewsMessage extends ResponseBaseMessage {
    /**
     * 图文消息个数，限制为10条以内.
     */
    @XmlElement(name = "ArticleCount")
    private int articleCount;
    /**
     * 多条图文消息信息，默认第一个item为大图.
     */
    @XmlElement(name = "Articles")
    private List<Article> articles;
}
