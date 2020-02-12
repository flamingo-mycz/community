package cn.mycz.community.dto;

import cn.mycz.community.pojo.User;
import lombok.Data;

/**
 * @author 木已成舟
 * @date 2020/1/31
 */
@Data
public class GithubUser {

    private String login;
    private String name;
    private Integer id;
    private String bio;
    private String avatarUrl;

    /**
     * 将github对象转换成user对象
     * @param accountId
     * @param token
     * @return
     */
    public User convertToUser( Integer accountId, String token) {
        long currentTimeMillis = System.currentTimeMillis();

        User user = new User();
        user.setName(this.getName() == null ? this.getLogin() : this.getName());
        user.setToken(token);
        user.setAccountId(accountId);
        user.setGmtCreate(currentTimeMillis);
        user.setGmtModified(currentTimeMillis);
        user.setAvatarUrl(this.getAvatarUrl());

        return user;
    }
}
