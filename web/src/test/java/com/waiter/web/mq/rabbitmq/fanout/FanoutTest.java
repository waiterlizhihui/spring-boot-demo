package com.waiter.web.mq.rabbitmq.fanout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName FanoutTest
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/30 17:20
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FanoutTest {
    @Autowired
    private FanoutSender sender;

    @Test
    public void fanoutSender() {
        sender.send();
    }
}
