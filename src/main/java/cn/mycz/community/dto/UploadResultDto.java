package cn.mycz.community.dto;

import lombok.Data;

/**
 * @author 木已成舟
 * @date 2020/2/10
 */
@Data
public class UploadResultDto {
    private int success;
    private String message;
    private String url;

}
