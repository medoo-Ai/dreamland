package blog.dreamland.com.controller;

import blog.dreamland.com.common.RandStringUtils;
import blog.dreamland.com.common.SendMessageUtil;
import blog.dreamland.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @auther SyntacticSugar
 * @data 2019/3/4 0004下午 5:37
 */
@Controller
public class MessageController extends BaseController {


    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    @Qualifier("jmsQueueTemplate")
    private JmsTemplate jmsTemplate;

    @RequestMapping("sendMessage")
    public String send(Model model,
                       @RequestParam(value ="phone" ,required = false)String phone){

        //  success     fail
        HashMap<String, Object> map = new HashMap<>();
        // 获取验证码 发message   存redis   KV
        String code = RandStringUtils.getCode();
        SendMessageUtil.sendMessages(code, phone);
        redisTemplate.opsForValue().set(phone,code , 1, TimeUnit.MINUTES);
        //todo



        return "";
    }


}
