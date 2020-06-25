package cn.bfay.lion.wechat.model;

import lombok.Builder;
import lombok.Data;

/**
 * TemplateMessageNoticeData.
 *
 * @author wangjiannan
 */
@Data
@Builder
public class TemplateMessageNoticeData {
    private Keyword keyword1;
    private Keyword keyword2;
    private Keyword keyword3;
    private Keyword keyword4;
    private Keyword keyword5;
    private Keyword keyword6;

    @Data
    @Builder
    public static class Keyword {
        private String value;
    }
}
