package cn.mycz.community.controller;

import cn.mycz.community.mapper.QuestionMapper;
import cn.mycz.community.pojo.Question;
import cn.mycz.community.pojo.User;
import cn.mycz.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 木已成舟
 * @date 2020/2/2
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;

    /**
     * 点击编辑时跳转此方法
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Question question = questionMapper.findById(id);
        model.addAttribute("question", question);
        model.addAttribute("id", id);
        return "publish";
    }

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    /**
     * 用户发布问题
     * @param question
     * @param request
     * @param model
     * @return
     */
    @PostMapping("/publish")
    public String doPublish(Question question, HttpServletRequest request, Model model) {

        //验证是否登录
        User user = (User) request.getSession().getAttribute("user");
        if(user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        //校验用户输入
        if(StringUtils.isEmpty(question.getTitle())) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }

        if(StringUtils.isEmpty(question.getDescription())) {
            model.addAttribute("error", "描述不能为空");
            return "publish";
        }

        if(StringUtils.isEmpty(question.getTag())) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        if (question.getId() == null) {
            //创建question
            questionService.create(question, user.getAccountId());
        } else {
            //修改question
            questionService.update(question);
        }

        return "redirect:/";
    }
}
