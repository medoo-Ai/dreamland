package blog.dreamland.com.mq;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @auther SyntacticSugar
 * @data 2019/3/3 0003下午 7:52
 * 定义监听器
 * 监听器使用@lisneter
 */
@Component
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        MapMessage mapMessage = (MapMessage) message;
        try {
            // code  phone
            SendMessageUtil.sendMessages(mapMessage.getString("code"), mapMessage.getString("phone"));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
