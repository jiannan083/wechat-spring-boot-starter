package cn.bfay.wechat.model.message;

import java.util.List;

/**
 * 回复消息-图文消息.
 *
 * @author wangjiannan
 */
public class ResponseNewsMessage extends ResponseBaseMessage {
    /**
     * 图文消息个数，限制为10条以内.
     */
    private int ArticleCount;
    /**
     * 多条图文消息信息，默认第一个item为大图.
     */
    private List<Article> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }

    @Override
    public String toString() {
        return "ResponseNewsMessage{" +
                "ArticleCount=" + ArticleCount +
                ", Articles=" + Articles +
                '}';
    }
}
