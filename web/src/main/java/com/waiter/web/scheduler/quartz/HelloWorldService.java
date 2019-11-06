package com.waiter.web.scheduler.quartz;

import org.springframework.stereotype.Service;

/**
 * @ClassName HelloWorldService
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/9/4 16:13
 * @Version 1.0
 */
@Service
public class HelloWorldService {
    public void sayHello() {
        System.out.println("Quartz:Hello World");
    }
}
