package cn.mycz.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 木已成舟
 * @date 2020/1/30
 */
@Controller
public class IndexController {

    //ctrl + P -> 查看参数
    @GetMapping("/")
    public String hello() {
        return "index";
    }
}
