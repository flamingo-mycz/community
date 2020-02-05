package cn.mycz.community.service;

import cn.mycz.community.dto.PaginationDto;
import cn.mycz.community.dto.QuestionDto;
import cn.mycz.community.mapper.QuestionMapper;
import cn.mycz.community.mapper.UserMapper;
import cn.mycz.community.pojo.Question;
import cn.mycz.community.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 木已成舟
 * @date 2020/2/3
 */
@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDto list(Integer page, Integer size) {

        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDto> questionDtos = new ArrayList<>();
        PaginationDto paginationDto = new PaginationDto();
        for (Question question : questions) {
            User user = userMapper.findUserByAccountId(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtos.add(questionDto);
        }
        paginationDto.setQuestions(questionDtos);
        Integer totalCount = questionMapper.count();
        paginationDto.setPagination(totalCount, page, size);

        return paginationDto;
    }

    public PaginationDto listByUser(String accountId, Integer page, Integer size) {
        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.listByUser(accountId, offset, size);
        List<QuestionDto> questionDtos = new ArrayList<>();
        PaginationDto paginationDto = new PaginationDto();
        for (Question question : questions) {
            User user = userMapper.findUserByAccountId(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtos.add(questionDto);
        }
        paginationDto.setQuestions(questionDtos);
        Integer totalCount = questionMapper.countByUser(accountId);
        paginationDto.setPagination(totalCount, page, size);

        return paginationDto;
    }
}
