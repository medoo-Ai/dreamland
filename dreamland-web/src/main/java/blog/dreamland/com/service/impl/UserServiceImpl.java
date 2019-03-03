package blog.dreamland.com.service.impl;

import blog.dreamland.com.dao.UserMapper;
import blog.dreamland.com.entity.User;
import blog.dreamland.com.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther SyntacticSugar
 * @data 2019/3/1 0001下午 3:57
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 注册
     */
    @Transactional
    @Override
    public int register(User user) {
        //先查询用户
//        List<User> userList = userMapper.select(user);
//        if (userList.isEmpty()) {
//            int insert = userMapper.insert(user);
//            // 插入数据库
//            return insert;
//        }
//        return 0;
        return userMapper.insert(user);
    }

    /**
     * 登录
     */
    @Override
    public User login(String email, String password) {
        // 查询数据库
        User user = new User();
        user.setPassword(password);
        user.setEmail(email);
        List<User> users = userMapper.select(user);
        if (users.size() == 1) {
            //存在
            return users.get(0);
        }
        return null;
    }

    /**
     * 通过email 查找user
     */
    @Override
    public User findUserByEmail(String email) {
        User user = new User();
        user.setEmail(email);
        List<User> users = userMapper.select(user);
        if (users.size() == 1) {
            //存在
            return users.get(0);
        }
        return null;
    }

    /**
     * 通过手机号查找user
     */
    @Override
    public User findUserByPhone(String phone) {
        User user = new User();
        user.setPhone(phone);
        List<User> users = userMapper.select(user);
        if (users.size() == 1) {
            //存在
            return users.get(0);
        }
        return null;
    }

    /**
     * 通过id 查找user
     */
    @Override
    public User findUserById(String id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user != null) {
            return user;
        }
        return null;
    }

    /**
     * 删除用户通过email
     */
    @Transactional
    @Override
    public void deleteByEmail(String email) {
        User user = new User();
        int delete = userMapper.delete(user);
        if (delete != 1) {
            // 删除失败
        }
    }

    /**
     * 更新账户信息
     */
    @Override
    public void update(User user) {
        int i = userMapper.updateByPrimaryKeySelective(user);
    }
}
