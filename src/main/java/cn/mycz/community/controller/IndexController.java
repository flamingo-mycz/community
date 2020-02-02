package cn.mycz.community.controller;

import cn.mycz.community.mapper.UserMapper;
import cn.mycz.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 木已成舟
 * @date 2020/1/30
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    //ctrl + P -> 查看参数
    @GetMapping("/")
    public String hello(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
        	if ("token".equals(cookie.getName())) {
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if(user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }
        return "index";
    }
}
