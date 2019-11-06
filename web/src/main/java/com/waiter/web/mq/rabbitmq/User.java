package com.waiter.web.mq.rabbitmq;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/29 19:22
 * @Version 1.0
 */
@Data
public class User implements Serializable {
    private String name;
    private String pass;
}
