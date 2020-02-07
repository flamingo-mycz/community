package cn.mycz.community.controller;

import cn.mycz.community.dto.QuestionDto;
import cn.mycz.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 木已成舟
 * @date 2020/2/5
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 问题详情，当用户点击标题时跳转此方法
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id, Model model) {
        QuestionDto questionDto = questionService.getQuestionDtoById(id);
        questionService.increaseViewById(id);
        model.addAttribute("question", questionDto);
        return "question";
    }

}
