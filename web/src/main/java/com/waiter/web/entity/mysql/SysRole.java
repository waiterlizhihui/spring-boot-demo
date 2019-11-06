package com.waiter.web.entity.mysql;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SysRole
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/9 15:09
 * @Version 1.0
 */
@Data
public class SysRole implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String role;
    private String description;
}
