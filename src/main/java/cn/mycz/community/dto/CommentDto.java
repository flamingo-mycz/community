package cn.mycz.community.dto;

import cn.mycz.community.pojo.User;
import lombok.Data;

/**
 * @author 木已成舟
 * @date 2020/2/8
 */
@Data
public class CommentDto {

    private Integer id;
    private String content;
    private Integer parentId;
    private Integer type;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer likeCount;

    private User user;
}
