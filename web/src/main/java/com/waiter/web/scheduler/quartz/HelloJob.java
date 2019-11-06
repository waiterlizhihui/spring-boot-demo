package com.waiter.web.scheduler.quartz;

import com.waiter.utils.DateUtils;
import com.waiter.web.scheduler.quartz.base.BaseJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;

import java.util.Date;

/**
 * @ClassName HelloJob
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/9/5 11:50
 * @Version 1.0
 */
@Slf4j
public class HelloJob implements BaseJob {
    @Override
    public void execute(JobExecutionContext context){
        log.error("Hello Job执行时间:{}", new Date());
    }
}
