package cn.mycz.community.service;

import cn.mycz.community.dto.QuestionDto;
import cn.mycz.community.exception.CustomizeErrorCode;
import cn.mycz.community.exception.CustomizeException;
import cn.mycz.community.mapper.QuestionMapper;
import cn.mycz.community.pojo.Question;
import cn.mycz.community.pojo.QuestionExample;
import cn.mycz.community.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 木已成舟
 * @date 2020/2/3
 */
@Service
public class QuestionService {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 返回数据库的总行数
     * @return
     */
    public Integer totalCount() {
        return  (int) questionMapper.countByExample(new QuestionExample());
    }

    /**
     * 根据Creator计算个数
     * @param accountId
     * @return
     */
    public Integer countByCreator(Integer accountId) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(accountId);
        return (int) questionMapper.countByExample(questionExample);
    }

    /**
     * 创建一个Question
     * @param question
     * @param creator
     */
    public void create(Question question, Integer creator) {

        if (creator == null) {
            throw new NullPointerException();
        }

        question.setCreator(creator);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(System.currentTimeMillis());
        questionMapper.insertSelective(question);
    }

    /**
     * 更新Question
     * @param question
     */
    public void update(Question question) {
        question.setGmtModified(System.currentTimeMillis());
        if(question.getCreator() == null)
            throw new NullPointerException();
        int updated = questionMapper.updateByPrimaryKeySelective(question);
        if (updated != 1) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
    }

    /**
     * 根据Question的id获取Question的详情（QuestionDto）
     * @param id
     * @return
     */
    public QuestionDto getQuestionDtoById(Integer id) {
        Question question = findById(id);
        User user = userService.findByAccountId(question.getCreator());
        QuestionDto questionDto = new QuestionDto(question, user);
        return questionDto;
    }

    /**
     * 根据Id查询Question
     * @param id
     * @return
     */
    public Question findById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        return question;
    }

}
