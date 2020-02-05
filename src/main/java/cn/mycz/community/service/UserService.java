package cn.mycz.community.service;

import cn.mycz.community.mapper.UserMapper;
import cn.mycz.community.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 木已成舟
 * @date 2020/2/5
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public boolean hasUser(Integer accountId) {
        User user = userMapper.findByAccountId(accountId);
        return user != null;
    }

    public void update(User user) {
        user.setGmtModified(System.currentTimeMillis());
        userMapper.update(user);
    }
}
