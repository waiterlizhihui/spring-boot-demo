package com.waiter.web.mq.rabbitmq.many;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName Sender1
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/29 18:57
 * @Version 1.0
 */
@Component
public class Sender1 {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(int i) {
        String content = "sender1 send: " + i;
        System.out.println("Sender1 send:" + content);
        this.rabbitTemplate.convertAndSend("waiter",content);
    }
}
