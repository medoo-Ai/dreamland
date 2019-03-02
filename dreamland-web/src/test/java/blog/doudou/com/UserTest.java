package blog.doudou.com;

import blog.dreamland.com.entity.User;
import blog.dreamland.com.service.UserService;
import blog.dreamland.com.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @auther SyntacticSugar
 * @data 2019/3/1 0001下午 10:39
 */
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml","classpath:spring-mvc.xml"})
public class UserTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserService userService;

    @Test
    public  void saveUser(){
        User user = new User();
        user.setNickName("小猪佩奇");
        user.setEmail("kyelic@qq.com");
        user.setPhone("13066668888");
        userService.register(user);

    }
}
