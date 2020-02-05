package cn.mycz.community.controller;

import cn.mycz.community.dto.AccessTokenDto;
import cn.mycz.community.dto.GithubUser;
import cn.mycz.community.mapper.UserMapper;
import cn.mycz.community.provider.GithubProvider;
import cn.mycz.community.pojo.User;
import cn.mycz.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author 木已成舟
 * @date 2020/1/31
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    /**
     * github回调的方法，通过此方法进行用户登录
     * @param code
     * @param state
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        AccessTokenDto accessTokenDto = new AccessTokenDto(clientId, clientSecret,code,redirectUri,state);
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null) {
            //登录成功，写cookie和session
            Integer accountId =  githubUser.getId();
            String token = UUID.randomUUID().toString();
            User user = githubUser.convertToUser(accountId, token);
            request.getSession().setAttribute("user", user);
            if (userService.hasUser(user.getAccountId())) {
                userService.update(user);
            } else {
                userMapper.insert(user);
            }
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        } else {
            //登录失败
            return "redirect:/";
        }
    }

    /**
     * 用户退出
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        //删除session
        request.getSession().removeAttribute("user");
        //删除cookie
        Cookie cookie = new Cookie("token", null);//删除前必须要new 一个value为空；
        cookie.setPath("/");
        cookie.setMaxAge(0);//生命周期设置为0
        response.addCookie(cookie);
        return "redirect:/";
    }
}
