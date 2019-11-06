package com.waiter.web.dao.mysql.quartz;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.waiter.web.entity.quartz.JobAndTrigger;

public interface JobMapper {
    /**
     * 查询定时作业和触发器列表
     *
     * @return
     */
    IPage<JobAndTrigger> list(Page page);
}
