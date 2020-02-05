package cn.mycz.community.dto;

import lombok.Data;

/**
 * @author 木已成舟
 * @date 2020/1/31
 */
@Data
public class GithubUser {

    private String login;
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;

}
