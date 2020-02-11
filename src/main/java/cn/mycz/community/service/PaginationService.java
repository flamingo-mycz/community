package cn.mycz.community.service;

import cn.mycz.community.dto.NotificationDto;
import cn.mycz.community.dto.PaginationDto;
import cn.mycz.community.dto.QuestionDto;
import cn.mycz.community.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 木已成舟
 * @date 2020/2/6
 */
@Service
public class PaginationService {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    /**
     * 分页方法
     * @param page
     * @param size
     * @return
     */
    public PaginationDto listQuestions(Integer page, Integer size, Integer accountId) {

        Integer offset = size * (page - 1);
        int totalCount;
        QuestionExample questionExample = new QuestionExample();
        //根据用户列表
        if (accountId != null) {
            questionExample.createCriteria().andCreatorEqualTo(accountId);
            totalCount = questionService.countByCreator(accountId);
        } else {
            totalCount = questionService.totalCount();
        }
        List<Question> questions = questionService.list(size, offset, questionExample);

        List<QuestionDto> questionDtos = new ArrayList<>();
        PaginationDto<QuestionDto> paginationDto = new PaginationDto<>();
        for (Question question : questions) {
            Integer creator = question.getCreator();
            User user = userService.findByAccountId(creator);
            QuestionDto questionDto = new QuestionDto(question, user);
            questionDtos.add(questionDto);
        }
        paginationDto.setList(questionDtos);
        paginationDto.setPagination(totalCount, page, size);
        return paginationDto;
    }


    /**
     * 列出通知
     * @param page
     * @param size
     * @param accountId
     * @return
     */
    public PaginationDto listNotification(Integer page, Integer size, Integer accountId) {
        Integer offset = size * (page - 1);
        int totalCount;
        NotificationExample notificationExample = new NotificationExample();
        //根据用户列表
        notificationExample.createCriteria().andReceiverEqualTo(accountId);
        totalCount = questionService.countByCreator(accountId);
        List<Notification> notifications = notificationService.list(size, offset, notificationExample);

        List<NotificationDto> notificationDtos = new ArrayList<>();
        PaginationDto<NotificationDto> paginationDto = new PaginationDto<>();
        for (Notification notification : notifications) {
            Integer notifier = notification.getNotifier();
            User user = userService.findByAccountId(notifier);
            NotificationDto notificationDto = new NotificationDto(notification, user);
            notificationDtos.add(notificationDto);
        }
        paginationDto.setList(notificationDtos);
        paginationDto.setPagination(totalCount, page, size);
        return paginationDto;
    }
}
