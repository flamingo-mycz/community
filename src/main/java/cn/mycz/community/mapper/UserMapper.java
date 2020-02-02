package cn.mycz.community.mapper;

import cn.mycz.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 木已成舟
 * @date 2020/2/1
 */
@Mapper
@Repository
public interface UserMapper {

    @Insert(" insert into user(name, account_id, token, gmt_create, gmt_modified) values(#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified}) ")
    void insert(User user);
}
