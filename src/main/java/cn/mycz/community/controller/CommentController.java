package cn.mycz.community.controller;

import cn.mycz.community.dto.CommentDto;
import cn.mycz.community.dto.ResultDto;
import cn.mycz.community.exception.CustomizeErrorCode;
import cn.mycz.community.pojo.Comment;
import cn.mycz.community.pojo.User;
import cn.mycz.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 木已成舟
 * @date 2020/2/7
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    @ResponseBody
    public Object post(@RequestBody CommentDto commentDto, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            System.out.println("未登录");
            return ResultDto.errorOf(CustomizeErrorCode.USER_NO_LOGIN);
        }

        Comment comment = commentDto.build(user.getAccountId());
        commentService.insert(comment);

        return ResultDto.ok();
    }
}
