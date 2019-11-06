package com.waiter.web.config.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @ClassName RabbitConfig
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/29 17:32
 * @Version 1.0
 */
@Configuration
public class RabbitConfig {
    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }

    @Bean
    public Queue manyQueue() {
        return new Queue("waiter");
    }

    @Bean
    public Queue objectQueue() {
        return new Queue("object");
    }
}
