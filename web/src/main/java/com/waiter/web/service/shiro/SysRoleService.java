package com.waiter.web.service.shiro;

import com.waiter.web.dao.mysql.shiro.SysRoleMapper;
import com.waiter.web.entity.mysql.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @ClassName SysRoleService
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/9 15:19
 * @Version 1.0
 */
@Service
public class SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    public List<SysPermission> selectPermissionsByRoleId(Integer roleId) {
        List<SysPermission> permissionList = sysRoleMapper.selectPermissionsByRoleId(roleId);
        return permissionList;
    }

}
