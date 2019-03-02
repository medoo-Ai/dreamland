package blog.dreamland.com.email;

import javax.mail.PasswordAuthentication;

/**
 * @auther SyntacticSugar
 * @data 2019/3/2 0002 下午 11:29
 */

public class MyAuthenticator extends javax.mail.Authenticator {
    private String strUser;
    private String strPwd;
    public MyAuthenticator(String user, String password) {
        this.strUser = user;
        this.strPwd = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(strUser, strPwd);
    }
}
