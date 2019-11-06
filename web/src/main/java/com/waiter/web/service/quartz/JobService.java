package com.waiter.web.service.quartz;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.waiter.web.dao.mysql.quartz.JobMapper;
import com.waiter.web.entity.quartz.JobAndTrigger;
import com.waiter.web.entity.quartz.JobForm;
import com.waiter.web.utils.JobUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName JobService
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/9/5 9:21
 * @Version 1.0
 */
@Service
@Slf4j
public class JobService {
    private final Scheduler scheduler;

    private final JobMapper jobMapper;

    @Autowired
    public JobService(Scheduler scheduler, JobMapper jobMapper) {
        this.scheduler = scheduler;
        this.jobMapper = jobMapper;
    }

    /**
     * 添加并启动定时任务
     *
     * @param form
     */
    public void addJob(JobForm form) throws Exception {
        scheduler.start();
        JobDetail jobDetail = JobBuilder.newJob(JobUtil.getClass(form.getJobClassName()).getClass())
                .withIdentity(form.getJobClassName(), form.getJobGroupName())
                .build();

        CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule(form.getCronExpression());

        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(form.getJobClassName())
                .withSchedule(cron).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
            log.error("【定时任务】创建失败！", e);
            throw new Exception("【定时任务】创建失败！");
        }
    }

    /**
     * 删除定时任务
     * @param form
     * @throws SchedulerException
     */
    public void deleteJob(JobForm form) throws SchedulerException {
        scheduler.pauseTrigger(TriggerKey.triggerKey(form.getJobClassName(), form.getJobGroupName()));
        scheduler.unscheduleJob(TriggerKey.triggerKey(form.getJobClassName(), form.getJobGroupName()));
        scheduler.deleteJob(JobKey.jobKey(form.getJobClassName(), form.getJobGroupName()));
    }

    /**
     * 暂停定时任务
     * @param form
     * @throws SchedulerException
     */
    public void pauseJob(JobForm form) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(form.getJobClassName(), form.getJobGroupName()));
    }

    /**
     * 恢复定时任务
     * @param form
     * @throws SchedulerException
     */
    public void resumeJob(JobForm form) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(form.getJobClassName(), form.getJobGroupName()));
    }

    /**
     * 重新调度定时任务
     * @param form
     * @throws Exception
     */
    public void cronJob(JobForm form) throws Exception {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(form.getJobClassName(), form.getJobGroupName());

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(form.getCronExpression());

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder).build();

            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("【定时任务】更新失败！", e);
            throw new Exception("【定时任务】创建失败！");
        }
    }

    /**
     * 查询定时任务列表
     * @param page
     * @return
     */
    public IPage<JobAndTrigger> list(Page<JobAndTrigger> page) {
        return jobMapper.list(page);
    }
}
