package com.waiter.web.dao.mysql.shiro;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.waiter.web.entity.mysql.SysPermission;
import org.apache.ibatis.annotations.Mapper;

@DS("master")
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

}
