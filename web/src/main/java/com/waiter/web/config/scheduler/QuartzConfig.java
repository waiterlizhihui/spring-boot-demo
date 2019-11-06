package com.waiter.web.config.scheduler;

//import com.waiter.web.scheduler.quartz.HiJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName QuartzConfig
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/9/2 19:42
 * @Version 1.0
 */
@Configuration
public class QuartzConfig {
//    @Bean
//    public JobDetail myJobDetail() {
//        JobDetail jobDetail = JobBuilder.newJob(HiJob.class)
//                .withIdentity("myJob1", "myJobGroup1")
//                .usingJobData("job_param", "job_param1")
//                .storeDurably()
//                .build();
//        return jobDetail;
//    }

//    @Bean
//    public Trigger myTrigger() {
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .forJob(myJobDetail())
//                .withIdentity("myTrigger1", "myTriggerGroup1")
//                .usingJobData("job_trigger_param", "job_trigger_param1")
//                .startNow()
//                .withSchedule(CronScheduleBuilder.cronSchedule("* * 5 * * ?"))
//                .build();
//        return trigger;
//    }
}
