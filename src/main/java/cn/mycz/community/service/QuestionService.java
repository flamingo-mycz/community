package cn.mycz.community.service;

import cn.mycz.community.dto.QuestionDto;
import cn.mycz.community.exception.CustomizeErrorCode;
import cn.mycz.community.exception.CustomizeException;
import cn.mycz.community.mapper.QuestionExtMapper;
import cn.mycz.community.mapper.QuestionMapper;
import cn.mycz.community.pojo.Question;
import cn.mycz.community.pojo.QuestionExample;
import cn.mycz.community.pojo.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private QuestionExtMapper questionExtMapper;

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

        question.setViewCount(0);
        question.setCommentCount(0);
        question.setLikeCount(0);
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
        return question;
    }

    /**
     * 增加阅读数
     * @param id
     */
    public void increaseViewById(Integer id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.increaseView(question);
    }

    /**
     * 增加阅读数
     * @param question
     */
    public void increaseComment(Question question) {
        question.setCommentCount(1);
        questionExtMapper.increaseComment(question);
    }


    /**
     * 根据tag模糊搜索相关的问题
     * @param questionDto
     * @return
     */
    public List<QuestionDto> selectRelated(QuestionDto questionDto) {
        String tag = questionDto.getTag();

        if(StringUtils.isEmpty(tag)) {
            return new ArrayList<>();
        }

        String tagRegex = tag.replaceAll(",", "|");
        Question question = new Question();
        question.setId(questionDto.getId());
        question.setTag(tagRegex);

        List<Question> questions = questionExtMapper.selectRelated(question);
        List<QuestionDto> questionDtos = questions.stream().map(q -> new QuestionDto(q, userService.findByAccountId(q.getCreator()))).collect(Collectors.toList());

        return questionDtos;
    }

    /**
     * 问题的分页
     * @param size
     * @param offset
     * @param questionExample
     * @return
     */
    public List<Question> list(Integer size, Integer offset, QuestionExample questionExample) {
        questionExample.setOrderByClause("gmt_create desc");
        return questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));
    }

}
