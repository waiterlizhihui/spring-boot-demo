package com.waiter.web.mq.rabbitmq.hello;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName HelloSender
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/29 17:39
 * @Version 1.0
 */
@Component
public class HelloSender {
    @Autowired
    private AmqpTemplate rabbittTemplate;

    public void send() throws InterruptedException {
        for(int i=0;i<5;i++){
            String content = "hello " + new Date();
            System.out.println("发送了消息: " + content);
            this.rabbittTemplate.convertAndSend("hello",content);
            Thread.sleep(1000);
        }

    }
}
