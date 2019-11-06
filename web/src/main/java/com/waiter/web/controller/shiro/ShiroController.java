package com.waiter.web.controller.shiro;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName HomeController
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/12 10:09
 * @Version 1.0
 */
@Controller
public class ShiroController {
//    @RequestMapping({"/", "index"})
//    public String index() {
//        return "index";
//    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        System.out.println("开始登录");
        return "shiro/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginFail(HttpServletRequest request, HttpServletResponse response, Model model) {
        System.out.println("登录失败");
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("登录错误信息:" + exception);
        String msg = "";
        if (exception == null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("账号不存在");
                msg = "账号不存在";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("密码错误");
                msg = "密码错误";
            } else {
                msg = exception;
            }
        }
        model.addAttribute("msg", msg);
        return "shiro/login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole() {
        System.out.println("没有权限");
        return "shiro/403";
    }

    @RequestMapping(value = "/success")
    public String success() {
        System.out.println("登录成功");
        return "shiro/index";
    }
}
