package cn.mycz.community.service;

import cn.mycz.community.dto.CommentDto;
import cn.mycz.community.enums.CommentType;
import cn.mycz.community.exception.CustomizeErrorCode;
import cn.mycz.community.exception.CustomizeException;
import cn.mycz.community.mapper.CommentMapper;
import cn.mycz.community.pojo.Comment;
import cn.mycz.community.pojo.CommentExample;
import cn.mycz.community.pojo.Question;
import cn.mycz.community.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 木已成舟
 * @date 2020/2/7
 */
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @Transactional
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() <= 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARENT_NOT_FOUND);
        }

        if (comment.getType() == null ) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARENT_NOT_FOUND);
        }

        if (comment.getType() == CommentType.COMMENT.getType()) {
            //回复评论
            Comment dbComment = findById(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
        } else {
            //回复问题
            Integer parentId = comment.getParentId();
            Question question = questionService.findById(parentId);
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            //增加评论数
            questionService.increaseComment(question);
        }

        commentMapper.insert(comment);
    }

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public Comment findById(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据parentId查找
     * @return
     */
    public Comment findByParentId(Integer parentId) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(parentId);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if (comments.size() != 0) {
            return comments.get(0);
        } else {
            return null;
        }
    }

    /**
     * 根据QuestionId列出这个问题的回复
     * @param questionId
     * @param type
     * @return
     */
    public List<CommentDto> listComment(Integer questionId, int type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(questionId).andTypeEqualTo(type);
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if (comments.size() == 0)
            return null;

//        获取去重的评论人
        Set<Integer> commentators = comments.stream().map(Comment::getCommentator).collect(Collectors.toSet());
        List<Integer> userAccountIds = new ArrayList<>(commentators);
        List<User> users = userService.findByAccountIds(userAccountIds);

        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(User::getAccountId, user -> user));

        List<CommentDto> commentDtos = comments.stream().map(comment -> {
            CommentDto commentDto = new CommentDto();
            BeanUtils.copyProperties(comment, commentDto);
            commentDto.setUser(userMap.get(comment.getCommentator()));
            return commentDto;
        }).collect(Collectors.toList());

        return commentDtos;
    }
}
