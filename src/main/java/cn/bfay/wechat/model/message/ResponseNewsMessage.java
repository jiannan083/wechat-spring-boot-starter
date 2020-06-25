package cn.bfay.wechat.model.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 回复消息-图文消息.
 *
 * @author wangjiannan
 */
public class ResponseNewsMessage extends ResponseBaseMessage {
    private static final long serialVersionUID = 5791038273561108321L;

    /**
     * 图文消息个数，限制为10条以内.
     */
    @JsonProperty("ArticleCount")
    private int articleCount;
    /**
     * 多条图文消息信息，默认第一个item为大图.
     */
    @JsonProperty("Articles")
    private List<Article> articles;

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "ResponseNewsMessage{" +
                "articleCount=" + articleCount +
                ", articles=" + articles +
                '}';
    }
}
