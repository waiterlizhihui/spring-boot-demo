package com.waiter.web.mq.rabbitmq.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName HelloTest
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/29 17:44
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloTest {
    @Autowired
    private HelloSender helloSender;

    @Test
    public void hello() throws InterruptedException {
        helloSender.send();
    }
}
