package com.waiter.web.mq.rabbitmq.object;

import com.waiter.web.mq.rabbitmq.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName ObjectSender
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/29 19:24
 * @Version 1.0
 */
@Component
public class ObjectSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(User user) {
        System.out.println("Send object :" + user.toString());
        this.rabbitTemplate.convertAndSend("object", user);
    }
}
