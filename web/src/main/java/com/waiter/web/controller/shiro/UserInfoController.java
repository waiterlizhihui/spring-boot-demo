package com.waiter.web.controller.shiro;//package com.waiter.demo.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName UserInfoController
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/12 10:28
 * @Version 1.0
 */
@Controller
@RequestMapping("/shiro")
public class UserInfoController {
    @RequestMapping("/userList")
    @RequiresPermissions("userInfo:view")
    public String userInfo() {
        return "shiro/userInfo";
    }

    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")
    public String userInfoAdd(){
        return "shiro/userInfoAdd";
    }

    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")
    public String userInfoDel(){
        return "shiro/userInfoDel";
    }

    @RequestMapping("/userEdit")
    @RequiresPermissions("userInfo:edit")
    public String userInfoEdit() {
        return "shiro/userInfoEdit";
    }

}
