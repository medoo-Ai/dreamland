package blog.doudou.com;

import blog.dreamland.com.mq.SendMessageUtil;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import org.junit.Test;

/**
 * @auther SyntacticSugar
 * @data 2019/3/3 0003下午 8:44
 */
public class MessageTest {


    @Test
    public  void test(){
        SendSmsResponse sendSmsResponse = SendMessageUtil.sendMessages("123456", "17301781426");
        System.out.println(sendSmsResponse.getCode());
        System.out.println(sendSmsResponse.getMessage());
        System.out.println(sendSmsResponse.getBizId());
        System.out.println(sendSmsResponse.getRequestId());
    }

}
