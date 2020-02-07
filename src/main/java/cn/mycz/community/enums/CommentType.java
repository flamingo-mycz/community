package cn.mycz.community.enums;

/**
 * @author 木已成舟
 * @date 2020/2/7
 */
public enum  CommentType {
    QUESTION(1),
    COMMENT(2);

    private int type;

    CommentType(Integer type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
