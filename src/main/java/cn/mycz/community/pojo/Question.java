package cn.mycz.community.pojo;

import lombok.Data;

/**
 * @author 木已成舟
 * @date 2020/2/2
 */
@Data
public class Question {

    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;


}
