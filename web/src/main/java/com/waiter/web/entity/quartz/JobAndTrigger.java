package com.waiter.web.entity.quartz;

import lombok.Data;

import java.awt.print.PrinterAbortException;
import java.math.BigInteger;

/**
 * @ClassName JobAndTrigger
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/9/4 19:33
 * @Version 1.0
 */
@Data
public class JobAndTrigger {
    /**
     * 定时任务名称
     */
    private String jobName;

    /**
     * 定时任务组
     */
    private String jobGroup;

    /**
     * 定时任务全类名
     */
    private String jobClassName;

    /**
     * 触发器名称
     */
    private String triggerName;

    /**
     * 触发器组
     */
    private String triggerGroup;

    /**
     * 重复间隔
     */
    private BigInteger repeatInterval;

    /**
     * 触发次数
     */
    private BigInteger timesTriggered;

    /**
     * cron表达式
     */
    private String cronExpression;

    /**
     * 时区
     */
    private String timeZoneId;

    /**
     * 定时任务状态
     */
    private String triggerState;
}
