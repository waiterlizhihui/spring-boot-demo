package com.waiter.web.service.shiro;

import com.waiter.web.dao.mysql.shiro.SysUserMapper;
import com.waiter.web.entity.mysql.SysRole;
import com.waiter.web.entity.mysql.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserService
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/8 19:13
 * @Version 1.0
 */
@Service
public class SysUserService {
    @Autowired
    private SysUserMapper userMapper;

    @Cacheable(value = "user", key = "'user_'+#id")
    public SysUser getUser(Long id) {
        System.out.println("-------开始查询-----------");
        SysUser user = userMapper.selectById(id);
        return user;
    }

    public List<SysRole> selectRolesByUserId(Integer userId) {
        List<SysRole> roleList = userMapper.selectRolesByUserId(userId);
        return roleList;
    }
}
