//package com.waiter.web.scheduler.quartz;
//
//import org.quartz.JobExecutionContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.quartz.QuartzJobBean;
//import org.springframework.stereotype.Component;
//
///**
// * @ClassName HiJob
// * @Description TOOD
// * @Author lizhihui
// * @Date 2019/9/4 16:12
// * @Version 1.0
// */
//@Component
//public class HiJob extends QuartzJobBean {
//    @Autowired
//    HelloWorldService helloWorldService;
//
//    @Override
//    protected void executeInternal(JobExecutionContext jobExecutionContext) {
//        helloWorldService.sayHello();
//        System.out.println("Hi! : " + jobExecutionContext.getJobDetail().getKey());
//    }
//}
