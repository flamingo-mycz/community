package cn.mycz.community.service;

import cn.mycz.community.dto.PaginationDto;
import cn.mycz.community.dto.QuestionDto;
import cn.mycz.community.mapper.QuestionMapper;
import cn.mycz.community.pojo.Question;
import cn.mycz.community.pojo.QuestionExample;
import cn.mycz.community.pojo.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 木已成舟
 * @date 2020/2/6
 */
@Service
public class PaginationService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    /**
     * 分页方法
     * @param page
     * @param size
     * @return
     */
    public PaginationDto list(Integer page, Integer size, Integer accountId) {

        Integer offset = size * (page - 1);
        int totalCount;
        QuestionExample questionExample = new QuestionExample();
        //根据用户列表
        if (accountId != null) {
            questionExample.createCriteria().andCreatorEqualTo(accountId);
            totalCount = questionService.countByCreator(accountId);
        } else {
            totalCount = questionService.totalCount();
        }
        questionExample.setOrderByClause("gmt_create desc");
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));

        List<QuestionDto> questionDtos = new ArrayList<>();
        PaginationDto paginationDto = new PaginationDto();
        for (Question question : questions) {
            Integer creator = question.getCreator();
            User user = userService.findByAccountId(creator);
            QuestionDto questionDto = new QuestionDto(question, user);
            questionDtos.add(questionDto);
        }
        paginationDto.setQuestions(questionDtos);
        paginationDto.setPagination(totalCount, page, size);
        return paginationDto;
    }

}
