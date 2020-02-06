package cn.mycz.community.controller;

import cn.mycz.community.dto.PaginationDto;
import cn.mycz.community.pojo.User;
import cn.mycz.community.service.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 木已成舟
 * @date 2020/2/5
 */
@Controller
public class ProfileController {

    @Autowired
    private PaginationService paginationService;

    /**
     * 根据navigation弹出的按钮切换页面，分别展示我的问题与最新登录
     * @param action
     * @param model
     * @param request
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size) {

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            return "redirect:/";
        }

        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }

        PaginationDto paginationDto = paginationService.list(page, size, user.getAccountId());
        model.addAttribute("pagination", paginationDto);

        return "profile";
    }
}
