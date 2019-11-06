package com.waiter.web.entity.mysql;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName Comment
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/8 16:47
 * @Version 1.0
 */
@Data
public class Comment {
    private Integer id;
    private Integer tid;
    private String content;
    private Date created;
}
