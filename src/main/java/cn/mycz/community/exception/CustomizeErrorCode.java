package cn.mycz.community.exception;

/**
 * @author 木已成舟
 * @date 2020/2/6
 */
public enum CustomizeErrorCode implements IErrorCode {

    QUESTION_NOT_FOUND("你找的问题不存在了，要不换一个试试~");

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
