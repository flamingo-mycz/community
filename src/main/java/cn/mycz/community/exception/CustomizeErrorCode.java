package cn.mycz.community.exception;

/**
 * @author 木已成舟
 * @date 2020/2/6
 */
public enum CustomizeErrorCode implements IErrorCode {

    QUESTION_NOT_FOUND(201, "你找的问题不存在了，要不换一个试试~"),
    TARGET_PARENT_NOT_FOUND(202, "未选中任何问题或评论进行回复"),
    USER_NO_LOGIN(203, "用户未登录，请登录后重试"),
    SYSTEM_ERROR(204, "服务冒烟了。。。"),
    COMMENT_NOT_FOUND(205, "你找的评论不存在了")
    ;

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


}
