package cn.bfay.wechat.model;

import java.io.Serializable;

/**
 * TemplateMessageNoticeData.
 *
 * @author wangjiannan
 */
public class TemplateMessageNoticeData implements Serializable {
    private static final long serialVersionUID = 7597340880309763383L;

    private Keyword keyword1;
    private Keyword keyword2;
    private Keyword keyword3;
    private Keyword keyword4;
    private Keyword keyword5;
    private Keyword keyword6;
    private Keyword keyword7;
    private Keyword keyword8;
    private Keyword keyword9;

    public Keyword getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(Keyword keyword1) {
        this.keyword1 = keyword1;
    }

    public Keyword getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(Keyword keyword2) {
        this.keyword2 = keyword2;
    }

    public Keyword getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(Keyword keyword3) {
        this.keyword3 = keyword3;
    }

    public Keyword getKeyword4() {
        return keyword4;
    }

    public void setKeyword4(Keyword keyword4) {
        this.keyword4 = keyword4;
    }

    public Keyword getKeyword5() {
        return keyword5;
    }

    public void setKeyword5(Keyword keyword5) {
        this.keyword5 = keyword5;
    }

    public Keyword getKeyword6() {
        return keyword6;
    }

    public void setKeyword6(Keyword keyword6) {
        this.keyword6 = keyword6;
    }

    public Keyword getKeyword7() {
        return keyword7;
    }

    public void setKeyword7(Keyword keyword7) {
        this.keyword7 = keyword7;
    }

    public Keyword getKeyword8() {
        return keyword8;
    }

    public void setKeyword8(Keyword keyword8) {
        this.keyword8 = keyword8;
    }

    public Keyword getKeyword9() {
        return keyword9;
    }

    public void setKeyword9(Keyword keyword9) {
        this.keyword9 = keyword9;
    }

    @Override
    public String toString() {
        return "TemplateMessageNoticeData{" +
                "keyword1=" + keyword1 +
                ", keyword2=" + keyword2 +
                ", keyword3=" + keyword3 +
                ", keyword4=" + keyword4 +
                ", keyword5=" + keyword5 +
                ", keyword6=" + keyword6 +
                ", keyword7=" + keyword7 +
                ", keyword8=" + keyword8 +
                ", keyword9=" + keyword9 +
                '}';
    }

    public static class Keyword implements Serializable {
        private static final long serialVersionUID = -7955153953501595948L;

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Keyword{" +
                    "value='" + value + '\'' +
                    '}';
        }
    }
}
