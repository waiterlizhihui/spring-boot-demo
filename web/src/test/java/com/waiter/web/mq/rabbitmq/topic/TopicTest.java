package com.waiter.web.mq.rabbitmq.topic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName TopicTest
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/30 16:53
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicTest {
    @Autowired
    private TopicSender sender;

    @Test
    public void topic() {
        sender.send();
    }

    @Test
    public void topic1() {
        sender.send1();
    }

    @Test
    public void topic2() {
        sender.send2();
    }
}
