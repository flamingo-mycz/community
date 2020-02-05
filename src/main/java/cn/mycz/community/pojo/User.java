package cn.mycz.community.pojo;

import lombok.Data;

/**
 * @author 木已成舟
 * @date 2020/2/1
 */
@Data
public class User {
    private Integer id;
    private String name;
    private Integer accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;

}
