package blog.dreamland.com.service.impl;

import blog.dreamland.com.entity.User;
import blog.dreamland.com.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @auther SyntacticSugar
 * @data 2019/3/1 0001下午 3:57
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public int register(User user) {
        return 0;
    }

    @Override
    public User login(String email, String password) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public User findUserByPhone(String phone) {
        return null;
    }

    @Override
    public User findUserById(String id) {
        return null;
    }

    @Override
    public void deleteByEmail(String email) {

    }

    @Override
    public void update(User user) {

    }
}
