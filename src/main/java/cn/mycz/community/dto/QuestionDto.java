package cn.mycz.community.dto;

import cn.mycz.community.pojo.Question;
import cn.mycz.community.pojo.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author 木已成舟
 * @date 2020/2/3
 */
@Data
public class QuestionDto extends Question {

    private User user;

    public QuestionDto() {}

    public QuestionDto(Question question, User user) {
        BeanUtils.copyProperties(question, this);
        setUser(user);
    }
}
