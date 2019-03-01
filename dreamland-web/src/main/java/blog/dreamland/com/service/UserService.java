package blog.dreamland.com.service;

import blog.dreamland.com.entity.User;

public interface UserService {

    int register(User user);
    User login(String email ,String password);
    User findUserByEmail(String email);
    User findUserByPhone(String phone);
    User findUserById(String id);
    void deleteByEmail(String email);
    void update(User user);
}
