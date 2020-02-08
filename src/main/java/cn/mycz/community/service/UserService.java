package cn.mycz.community.service;

import cn.mycz.community.mapper.UserMapper;
import cn.mycz.community.pojo.User;
import cn.mycz.community.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 木已成舟
 * @date 2020/2/5
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据accountId查找用户是否存在
     * @param accountId
     * @return
     */
    public boolean hasUser(Integer accountId) {
        return findByAccountId(accountId) != null;
    }

    /**
     * 更新User
     * @param user
     */
    public void update(User user) {
        user.setGmtModified(System.currentTimeMillis());
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        userMapper.updateByExample(user, userExample);
    }

    /**
     * 根据accountId查询User
     * @param accountId
     * @return
     */
    public User findByAccountId(Integer accountId) {
        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(accountId);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() != 0)
            return users.get(0);
        else
            return null;
    }

    /**
     * 根据accountId列表查询多个User
     * @param
     * @return
     */
    public List<User> findByAccountIds(List<Integer> userAccountIds) {
        UserExample example = new UserExample();
        example.createCriteria().andAccountIdIn(userAccountIds);
        List<User> users = userMapper.selectByExample(example);
        return users;
    }

    /**
     * 根据token查找用户
     * @param token
     * @return
     */
    public User findByToken(String token) {
        UserExample example = new UserExample();
        example.createCriteria().andTokenEqualTo(token);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() != 0)
            return users.get(0);
        else
            return null;
    }
}
