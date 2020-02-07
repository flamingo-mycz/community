package cn.mycz.community.dto;

import cn.mycz.community.pojo.Comment;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author 木已成舟
 * @date 2020/2/7
 */
@Data
public class CommentDto {
    private Integer parentId;
    private String content;
    private Integer type;

    public Comment build(Integer commentator) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(this, comment);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(commentator);
        comment.setLikeCount(0);
        return comment;
    }
}
