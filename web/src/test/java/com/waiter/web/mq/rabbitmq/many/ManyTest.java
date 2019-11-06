package com.waiter.web.mq.rabbitmq.many;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName ManyTest
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/29 19:07
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyTest {
    @Autowired
    private Sender1 sender1;

    @Autowired
    private Sender2 sender2;

    @Test
    public void oneToMany() {
        for(int i=0;i<40;i++){
            sender1.send(i);
        }
    }

    @Test
    public void manyToMany() {
        for(int i=0;i<40;i++){
            sender1.send(i);
            sender2.send(i);
        }
    }
}
