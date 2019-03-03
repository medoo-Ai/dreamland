package blog.dreamland.com.controller;

import blog.dreamland.com.common.CaptchaServlet;
import blog.dreamland.com.common.MD5Util;
import blog.dreamland.com.email.SendEmail;
import blog.dreamland.com.entity.User;
import blog.dreamland.com.service.RoleUserService;
import blog.dreamland.com.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
import java.util.concurrent.TimeUnit;


/**
 * @auther SyntacticSugar
 * @data 2019/3/2 0002上午 11:29
 */
@Controller
@RequestMapping("register")
public class RegisterController {

    public final static String SALT_VALUE = "QIUTU";

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Autowired
    private RoleUserService roleUserService;


    /**
     * 校验手机号
     *
     * @param model
     * @param phone
     * @return
     */
    @RequestMapping(value = "checkPhone", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> checkPhone(Model model, @RequestParam(value = "phone", required = false) String phone) {
        //查询mysql
        User user = userService.findUserByPhone(phone);
        HashMap<String, Object> hashMap = new HashMap<>();
        if (user == null) {
            hashMap.put("message", "success");
        } else {
            hashMap.put("message", "fail");
        }
        return hashMap;
    }

    /**
     * 校验验证码
     *
     * @param model
     * @param code
     * @return
     */
    @RequestMapping(value = "checkCode", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> checkCode(Model model, @RequestParam(value = "code", required = false) String code) {
        //从session中取出code
        RequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String sessionCode = (String) ((ServletRequestAttributes) attributes).getRequest().getSession().getAttribute(CaptchaServlet.VERCODE_KEY);
        // 比较
        HashMap<String, Object> hashMap = new HashMap<>();
        if (code.equals(sessionCode)) {
            hashMap.put("message", "success");
        } else {
            hashMap.put("message", "fail");
        }
        return hashMap;
    }

    /**
     * 邮件激活
     *
     * @param model
     * @param code
     * @return
     */
    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    public String doRegister(Model model,
                             @RequestParam(value = "email", required = false) String email,
                             @RequestParam(value = "password", required = false) String password,
                             @RequestParam(value = "phone", required = false) String phone,
                             @RequestParam(value = "nickName", required = false) String nickname,
                             @RequestParam(value = "code", required = false) String code) {
        //校验验证码
        if (StringUtils.isBlank(code)) {
            model.addAttribute("error", "验证码非法，请君重新注册");
            return "../register";
        }

        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String vercode = (String) attrs.getRequest().getSession().getAttribute("VERCODE_KEY");
        if (vercode == null) {
            model.addAttribute("error", "验证码超时");
            return "../register";
        }
        //和code 相等？
        if (!vercode.equalsIgnoreCase(code)) {
            model.addAttribute("error", "验证码不正确");
            return "../register";
        }

        //校验Email
        User userByEmail = userService.findUserByEmail(email);
        if (userByEmail != null) {
            model.addAttribute("error", "该邮件已经被注册");
            return "../register";
        } else {
            // 封装user
            User user = new User();
            user.setEmail(email);
            user.setImgUrl("images/45119881.jpg");   //图标
            user.setNickName(nickname);
            user.setPassword(MD5Util.encodeToHex(password + SALT_VALUE));
            user.setPhone(phone);
            user.setState("0");
            user.setState("0");
            //激活email

            String toHex = MD5Util.encodeToHex(password + email + SALT_VALUE);
            redisTemplate.opsForValue().set(email, toHex, 1, TimeUnit.DAYS);

            // 注册 并发送邮件
            userService.register(user);
            SendEmail.sendEmailMessage(email, toHex);
            String message = email + "," + toHex;
            model.addAttribute("message", message);
            return "/register/registerSuccess";

        }
    }

    /**
     * 邮件发送
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "sendEmail", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> sendEmail(Model model) {
        //从session中取出code email
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String email = attributes.getRequest().getParameter("email");
        String code = attributes.getRequest().getParameter("validateCode");
        SendEmail.sendEmailMessage(email, code);
        HashMap<String, Object> map = new HashMap<>();
        map.put("success", "success");
        return  map;
    }

    // todo

    /**
     * 激活邮件
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "activeCode", method = RequestMethod.GET)
    public String activeCode(Model model) {
        HashMap<String, Object> map = new HashMap<>();
        //从redis中取出code 通过email
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String email = attributes.getRequest().getParameter("email");
        String strcode = attributes.getRequest().getParameter("validateCode");
        String redisCode=redisTemplate.opsForValue().get(email);
        //判断用户时有否已激活
        //若 redisCode  失效，从新获取code

        User userByEmail = userService.findUserByEmail(email);
        if (userByEmail!=null&&"1".equals(userByEmail.getState())) {
            map.put("success","已经激活，请登录");
            model.addAttribute("success","已经激活，请登录");
            return "../login";
        }

        // 未激活请激活
        if (redisCode==null) {
            model.addAttribute("fail","激活失效，请重新注册");
            userService.deleteByEmail(email);
            return "/register/activeFail";
        }

        //激活码正确 OR  wrong
        if(StringUtils.isNotBlank( strcode ) && strcode.equals( redisCode )){
            userByEmail.setEnable( "1" );
            userByEmail.setState( "1" );
            userService.update( userByEmail );
            model.addAttribute( "email",email );
            return "/register/activeSuccess";
        }else {
            model.addAttribute( "fail","您的激活码错误,请重新激活！" );
            return "/register/activeFail";
        }
    }



}
