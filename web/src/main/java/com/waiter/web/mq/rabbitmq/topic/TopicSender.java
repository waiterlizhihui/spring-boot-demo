package com.waiter.web.mq.rabbitmq.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName TopicSender
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/29 19:48
 * @Version 1.0
 */
@Component
public class TopicSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String content = "hi, i am message all";
        System.out.println("Sender : " + content);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.1", content);
    }

    public void send1() {
        String content = "hi, i am message 1";
        System.out.println("Sender : " + content);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.message", content);
    }

    public void send2() {
        String content = "hi, i am message 2";
        System.out.println("Sender : " + content);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.messages", content);
    }
}
