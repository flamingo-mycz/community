package cn.mycz.community.exception;

/**
 * @author 木已成舟
 * @date 2020/2/6
 */
public class CustomizeException extends RuntimeException {

    private String message;

    public CustomizeException(String message) {
        this.message = message;
    }

    public CustomizeException(IErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
