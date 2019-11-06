package com.waiter.web.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TestController
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/9/27 11:07
 * @Version 1.0
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @ResponseBody
    @RequestMapping(value = "/test1", method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object> test1(@RequestParam(value = "urlStr", required = true) String urlStr,
                                    @RequestParam(value = "domain", required = true) String domain,
                                    @RequestParam(value = "expireType", defaultValue = "3") Integer expireType,
                                    @RequestParam(value = "key", required = true, defaultValue = "0@null") String key,
                                    @RequestParam(value = "mark", required = true, defaultValue = "") String mark,
                                    @RequestParam(value = "random", required = true, defaultValue = "0") long timestamp,
                                    @RequestParam(value = "extDomain", required = true, defaultValue = "t.cn") String extDomain,
                                    HttpServletRequest request,
                                    HttpServletResponse response){
        String x = request.getParameter("x");
        System.out.println(x);
        Map<String,Object> map = new HashMap<>();
        map.put("success","ok");
        return map;
    }

}
