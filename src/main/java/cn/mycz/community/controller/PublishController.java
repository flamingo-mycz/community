package cn.mycz.community.controller;

import cn.mycz.community.mapper.QuestionMapper;
import cn.mycz.community.pojo.Question;
import cn.mycz.community.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(Question question, HttpServletRequest request, Model model) {

        model.addAttribute("question", question);

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

        User user = (User) request.getSession().getAttribute("user");

        if(user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        question.setCreator(user.getAccountId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(System.currentTimeMillis());

        questionMapper.create(question);

        return "redirect:/";
    }
}
