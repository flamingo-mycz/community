package cn.mycz.community.mapper;

import cn.mycz.community.pojo.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionExtMapper {

    int increaseView(Question record);
}