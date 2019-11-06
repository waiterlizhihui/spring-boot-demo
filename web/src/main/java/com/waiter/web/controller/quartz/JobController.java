package com.waiter.web.controller.quartz;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.waiter.utils.StringUtils;
import com.waiter.web.common.ApiResponse;
import com.waiter.web.entity.quartz.JobAndTrigger;
import com.waiter.web.entity.quartz.JobForm;
import com.waiter.web.service.quartz.JobService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName JobController
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/9/5 10:35
 * @Version 1.0
 */
@RestController
@RequestMapping("/job")
@Slf4j
public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping
    public ResponseEntity<ApiResponse> addJob(@Valid JobForm form) {
        try {
            jobService.addJob(form);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ApiResponse.msg(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(ApiResponse.msg("操作成功"), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteJob(JobForm form) {
        if (StringUtils.isAnyBlank(form.getJobGroupName(), form.getJobClassName())) {
            return new ResponseEntity<>(ApiResponse.msg("参数不能为空"), HttpStatus.BAD_REQUEST);
        }

        try {
            jobService.deleteJob(form);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(ApiResponse.msg("删除成功"), HttpStatus.OK);
    }

    @PutMapping(params = "pause")
    public ResponseEntity<ApiResponse> pauseJob(JobForm form) {
        if (StringUtils.isAnyBlank(form.getJobGroupName(), form.getJobClassName())) {
            return new ResponseEntity<>(ApiResponse.msg("参数不能为空"), HttpStatus.BAD_REQUEST);
        }

        try {
            jobService.pauseJob(form);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(ApiResponse.msg("暂停成功"), HttpStatus.OK);
    }

    @PutMapping(params = "resume")
    public ResponseEntity<ApiResponse> resumeJob(JobForm form) {
        if (StringUtils.isAllBlank(form.getJobGroupName(), form.getJobClassName())) {
            return new ResponseEntity<>(ApiResponse.msg("参数不能为空"), HttpStatus.BAD_REQUEST);
        }

        try {
            jobService.resumeJob(form);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(ApiResponse.msg("恢复成功"), HttpStatus.OK);
    }

    @PutMapping(params = "cron")
    public ResponseEntity<ApiResponse> cronJob(@Valid JobForm form) {
        try {
            jobService.cronJob(form);
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponse.msg(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(ApiResponse.msg("修改成功"), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> jobList(Long currentPage, Long pageSize) {
        if(currentPage == null){
            currentPage = 0L;
        }

        if(pageSize == null){
            pageSize = 10L;
        }

        Page<JobAndTrigger> page = new Page<>();
        page.setCurrent(currentPage);
        page.setPages(pageSize);

        jobService.list(page);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("total", page.getTotal());
        map.put("data", page.getRecords());

        return ResponseEntity.ok(ApiResponse.ok(map));
    }
}
