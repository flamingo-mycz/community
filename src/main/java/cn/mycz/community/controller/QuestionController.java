package cn.mycz.community.controller;

import cn.mycz.community.dto.CommentDto;
import cn.mycz.community.dto.QuestionDto;
import cn.mycz.community.enums.CommentType;
import cn.mycz.community.service.CommentService;
import cn.mycz.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author 木已成舟
 * @date 2020/2/5
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    /**
     * 问题详情，当用户点击标题时跳转此方法
     * @param questionId
     * @param model
     * @return
     */
    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer questionId, Model model) {
        QuestionDto questionDto = questionService.getQuestionDtoById(questionId);
        questionService.increaseViewById(questionId);
        List<CommentDto> comments = commentService.listComment(questionId, CommentType.QUESTION.getType());
        List<QuestionDto> relatedQuestions = questionService.selectRelated(questionDto);
        model.addAttribute("question", questionDto);
        model.addAttribute("comments", comments);
        model.addAttribute("relatedQuestions", relatedQuestions);
        return "question";
    }

}
