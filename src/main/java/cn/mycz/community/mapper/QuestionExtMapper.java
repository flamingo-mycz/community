package cn.mycz.community.mapper;

import cn.mycz.community.pojo.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionExtMapper {

    int increaseView(Question record);

    int increaseComment(Question record);

    List<Question> selectRelated(Question question);
}