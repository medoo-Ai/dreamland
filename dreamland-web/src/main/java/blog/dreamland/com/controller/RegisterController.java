package blog.dreamland.com.controller;

import blog.dreamland.com.common.CaptchaServlet;
import blog.dreamland.com.entity.User;
import blog.dreamland.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.ws.rs.POST;
import java.util.HashMap;
import java.util.Map;


/**
 * @auther SyntacticSugar
 * @data 2019/3/2 0002上午 11:29
 */
@Controller
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private UserService userService;



    /**
     * 校验手机号
     * @param model
     * @param phone
     * @return
     */
    @RequestMapping(value = "checkPhone",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> checkPhone(Model model, @RequestParam(value = "phone",required = false)String phone){
        //查询mysql
        User user = userService.findUserByPhone(phone);
        HashMap<String, Object> hashMap = new HashMap<>();
        if (user==null) {
            hashMap.put("message", "success");
        }else {
            hashMap.put("message", "fail");
        }
        return hashMap;
    }

    /**
     * 校验验证码
     * @param model
     * @param code
     * @return
     */
    @RequestMapping(value = "checkCode",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> checkCode(Model model, @RequestParam(value = "code",required = false)String code){
        //从session中取出code
        RequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        String sessionCode = (String) ((ServletRequestAttributes) attributes).getRequest().getSession().getAttribute(CaptchaServlet.VERCODE_KEY);
        // 比较
        HashMap<String, Object> hashMap = new HashMap<>();
        if (code.equals(sessionCode)) {
            hashMap.put("message", "success");
        }else {
            hashMap.put("message", "fail");
        }
        return hashMap;
    }




}
