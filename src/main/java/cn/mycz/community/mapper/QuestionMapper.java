package cn.mycz.community.mapper;

import cn.mycz.community.pojo.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 木已成舟
 * @date 2020/2/2
 */
@Repository
@Mapper
public interface QuestionMapper {

    @Insert(" insert into question(title, description, gmt_create, gmt_modified, creator, tag) values(#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag}) ")
    void create(Question question);

    @Select(" select * from question ")
    List<Question> findAll();

    @Select(" select * from question limit #{offset}, #{size}")
    List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select(" select count(1) from question ")
    Integer count();

    @Select(" select * from question where creator = #{accountId} limit #{offset}, #{size} ")
    List<Question> listByUser(@Param("accountId") String accountId, @Param("offset") Integer offset, @Param("size") Integer size);

    @Select(" select count(1) from question where creator = #{accountId}")
    Integer countByUser(@Param("accountId") String accountId);
}
