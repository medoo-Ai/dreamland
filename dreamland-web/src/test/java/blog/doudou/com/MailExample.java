package blog.doudou.com;

import blog.dreamland.com.email.SendEmail;
import org.junit.Test;

/**
 * @auther SyntacticSugar
 * @data 2019/3/2 0002 下午 11:29
 */
public class MailExample {

    @Test
    public void testEmail() {
        String email = "1426132113@qq.com";
        String validateCode = "hello world";
        SendEmail.sendEmailMessage(email,validateCode);
    }
}
