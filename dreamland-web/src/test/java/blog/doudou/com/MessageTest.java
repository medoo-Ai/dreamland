package blog.doudou.com;

import blog.dreamland.com.common.SendMessageUtil;
import org.junit.Test;

/**
 * @auther SyntacticSugar
 * @data 2019/3/3 0003下午 8:44
 */
public class MessageTest {


    @Test
    public  void test(){
        SendMessageUtil.sendMessages("123456", "17301781426");
    }

}
