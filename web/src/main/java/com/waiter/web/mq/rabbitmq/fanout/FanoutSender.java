package com.waiter.web.mq.rabbitmq.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName FanoutSender
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/30 17:13
 * @Version 1.0
 */
@Component
public class FanoutSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String content = "hi,fanout msg ";
        System.out.println("Sender : " + content);
        this.rabbitTemplate.convertAndSend("fanoutExchange", "", content);
    }
}
