package cn.mycz.community.dto;

import cn.mycz.community.pojo.Notification;
import cn.mycz.community.pojo.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author 木已成舟
 * @date 2020/2/10
 */
@Data
@NoArgsConstructor
public class NotificationDto {
    private Integer id;
    private Long gmtCreate;
    private Integer status;
    private User notifier;
    private String resourceTitle;
    private String statement;

    public NotificationDto(Notification notification, User user) {
        BeanUtils.copyProperties(notification, this);
        this.setNotifier(user);
    }
}
