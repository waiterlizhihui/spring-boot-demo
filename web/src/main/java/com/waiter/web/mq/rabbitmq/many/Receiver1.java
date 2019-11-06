package com.waiter.web.mq.rabbitmq.many;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName Receiver1
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/29 18:58
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "waiter")
public class Receiver1 {
    @RabbitHandler
    public void process(String msg) {
        System.out.println("Receiver 1: " + msg);
    }
}
