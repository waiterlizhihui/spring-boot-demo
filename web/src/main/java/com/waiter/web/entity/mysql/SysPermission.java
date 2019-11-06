package com.waiter.web.entity.mysql;

import lombok.Data;

/**
 * @ClassName SysPermission
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/9 15:12
 * @Version 1.0
 */
@Data
public class SysPermission {
    private Integer id;
    private String name;
    private String resourceType;
    private String url;
    private String permission;
    private int parentId;
    private String parentIds;
}
