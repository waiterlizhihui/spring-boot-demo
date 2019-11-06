package com.waiter.web.mq.rabbitmq.hello;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName HelloReceiver
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/29 17:41
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {
    @RabbitHandler
    public void process(String hello){
        System.out.println("Receiver: " + hello);
    }
}
