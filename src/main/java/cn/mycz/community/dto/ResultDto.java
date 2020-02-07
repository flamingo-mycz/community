package cn.mycz.community.dto;

import cn.mycz.community.exception.CustomizeErrorCode;
import cn.mycz.community.exception.CustomizeException;
import lombok.Data;

/**
 * @author 木已成舟
 * @date 2020/2/7
 */
@Data
public class ResultDto {

    private Integer code;
    private String message;

    public static ResultDto errorOf(Integer code, String message) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }

    public static ResultDto errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

    public static ResultDto errorOf(CustomizeException e) {
        return errorOf(e.getCode(), e.getMessage());
    }

    public static ResultDto ok() {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(100);
        resultDto.setMessage("请求成功");
        return resultDto;
    }
}
