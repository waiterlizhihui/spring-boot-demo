package com.waiter.web.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName Scheduler1Task
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/9/2 19:24
 * @Version 1.0
 */
@Component
public class Scheduler1Task {
    private int count =0;

//    @Scheduled(cron = "*/6 * * * * ?")
    private void process(){
        System.out.println("schedulerTask1 runing " + (count++));
    }
}
