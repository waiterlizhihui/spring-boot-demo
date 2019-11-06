package com.waiter.web.dao.mysql.shiro;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.waiter.web.entity.mysql.SysRole;
import com.waiter.web.entity.mysql.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@DS("master")
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    List<SysUser> getUserList();

    IPage<SysUser> selectByPage(Page page);

    List<SysRole> selectRolesByUserId(@Param("sysUserId") Integer sysUserId);
}
