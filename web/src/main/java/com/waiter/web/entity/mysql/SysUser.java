package com.waiter.web.entity.mysql;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/6 16:41
 * @Version 1.0
 */
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Integer age;
    private String email;
    private String password;
    private String salt;
}
