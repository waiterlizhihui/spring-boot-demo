package com.waiter.web.mq.rabbitmq.object;

import com.waiter.web.mq.rabbitmq.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName ObjectReceiver
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/29 19:24
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "object")
public class ObjectReceiver {
    @RabbitHandler
    public void process(User user) {
        System.out.println("Receiver object:" + user);
        System.out.println("hhhhhhh");
    }
}
