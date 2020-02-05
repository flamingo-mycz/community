package cn.mycz.community.dto;

import lombok.Data;

/**
 * @author 木已成舟
 * @date 2020/1/31
 */
@Data
public class AccessTokenDto {

    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
