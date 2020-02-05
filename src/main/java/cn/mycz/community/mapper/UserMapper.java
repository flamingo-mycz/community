package cn.mycz.community.mapper;

import cn.mycz.community.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author 木已成舟
 * @date 2020/2/1
 */
@Mapper
@Repository
public interface UserMapper {

    @Insert(" insert into user(name, account_id, token, gmt_create, gmt_modified, avatar_url) values(#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl}) ")
    void insert(User user);

    @Select(" select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select(" select * from user where account_id = #{accountId}")
    User findUserByAccountId(@Param("accountId") String accountId);

    @Update(" update user set token = #{token} where account_id = #{accountId} ")
    void resetToken(@Param("accountId") String accountId, @Param("token") String token);
}
