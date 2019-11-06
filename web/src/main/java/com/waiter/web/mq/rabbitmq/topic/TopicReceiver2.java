package com.waiter.web.mq.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName TopicReceiver2
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/29 19:53
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "topic.messages")
public class TopicReceiver2 {
    @RabbitHandler
    public void process(String message) {
        System.out.println("Top Receiver2 : " + message);
    }
}
