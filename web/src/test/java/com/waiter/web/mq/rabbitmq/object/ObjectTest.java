package com.waiter.web.mq.rabbitmq.object;

import com.waiter.web.mq.rabbitmq.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName ObjectTest
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/29 19:27
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectTest {
    @Autowired
    private ObjectSender objectSender;

    @Test
    public void sendObject() {
        User user = new User();
        user.setName("waiter");
        user.setPass("123456789");
        objectSender.send(user);
    }
}
