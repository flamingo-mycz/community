package cn.mycz.community.service;

import cn.mycz.community.mapper.NotificationMapper;
import cn.mycz.community.pojo.Notification;
import cn.mycz.community.pojo.NotificationExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 木已成舟
 * @date 2020/2/10
 */
@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    /**
     * 添加通知
     * @param notification
     */
    public void insert(Notification notification) {
        notificationMapper.insert(notification);
    }

    /**
     * 通知的分页
     * @param size
     * @param offset
     * @param notificationExample
     * @return
     */
    public List<Notification> list(Integer size, Integer offset, NotificationExample notificationExample) {
        notificationExample.setOrderByClause("gmt_create desc");
        return notificationMapper.selectByExampleWithRowbounds(notificationExample, new RowBounds(offset, size));
    }
}
