package com.waiter.web.utils;

import com.waiter.web.scheduler.quartz.base.BaseJob;

/**
 * @ClassName JobUtil
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/9/5 9:27
 * @Version 1.0
 */
public class JobUtil {
    public static BaseJob getClass(String classname) throws Exception {
        Class<?> clazz = Class.forName(classname);
        return (BaseJob) clazz.newInstance();
    }
}
