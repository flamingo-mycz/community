package cn.mycz.community.controller;

import cn.mycz.community.dto.CommentCreateDto;
import cn.mycz.community.dto.CommentDto;
import cn.mycz.community.dto.ResultDto;
import cn.mycz.community.enums.CommentType;
import cn.mycz.community.exception.CustomizeErrorCode;
import cn.mycz.community.pojo.Comment;
import cn.mycz.community.pojo.User;
import cn.mycz.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public Object post(@RequestBody CommentCreateDto commentCreateDto, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            System.out.println("未登录");
            return ResultDto.errorOf(CustomizeErrorCode.USER_NO_LOGIN);
        }

        if (commentCreateDto == null || StringUtils.isEmpty(commentCreateDto.getContent())) {
            return ResultDto.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }

        Comment comment = commentCreateDto.build(user.getAccountId());
        commentService.insert(comment);

        return ResultDto.ok();
    }

    @GetMapping("/comment/{id}")
    @ResponseBody
    public ResultDto comments(@PathVariable(name = "id") Integer id) {
        List<CommentDto> comments = commentService.listComment(id, CommentType.COMMENT.getType());
        return ResultDto.ok(comments);
    }
}
