package cn.mycz.community.controller;

import cn.mycz.community.dto.PaginationDto;
import cn.mycz.community.service.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 木已成舟
 * @date 2020/1/30
 */
@Controller
public class IndexController {

    @Autowired
    private PaginationService paginationService;

    /**
     * 显示列表
     * @param model
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/")
    public String show(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size                        ) {

        PaginationDto pagination = paginationService.listQuestions(page, size, null);
        model.addAttribute("pagination", pagination);

        return "index";
    }
}
