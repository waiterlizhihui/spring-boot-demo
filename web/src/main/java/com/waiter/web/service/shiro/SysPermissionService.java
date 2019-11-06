package com.waiter.web.service.shiro;

import com.waiter.web.dao.mysql.shiro.SysPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysPermissionService
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/9 15:19
 * @Version 1.0
 */
@Service
public class SysPermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;
}
