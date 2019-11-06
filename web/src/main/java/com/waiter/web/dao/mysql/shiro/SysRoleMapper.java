package com.waiter.web.dao.mysql.shiro;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.waiter.web.entity.mysql.SysPermission;
import com.waiter.web.entity.mysql.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@DS("master")
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysPermission> selectPermissionsByRoleId(@Param("sysRoleId") Integer sysRoleId);
}
