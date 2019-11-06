package com.waiter.web.sso.shiro;

import com.waiter.web.entity.mysql.SysPermission;
import com.waiter.web.entity.mysql.SysRole;
import com.waiter.web.entity.mysql.SysUser;
import com.waiter.web.service.shiro.SysRoleService;
import com.waiter.web.service.shiro.SysUserService;
import com.waiter.web.utils.Md5Utils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName ShiroRealm
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/9 14:29
 * @Version 1.0
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 登录校验
     *
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        System.out.println("=========开始进行登录校验=============");
        Long id = Long.parseLong(String.valueOf(auth.getPrincipal()));
        SysUser user = sysUserService.getUser(id);

        if (user == null) {
            throw new AccountException();
        }

        user.setPassword(Md5Utils.stringToMd5(user.getPassword()));
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, user.getPassword().toCharArray(), null, getName()
        );

        return authenticationInfo;
    }

    /**
     * 权限校验
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("=========开始进行权限校验=============");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser user = (SysUser) principals.getPrimaryPrincipal();
        List<SysRole> roleList = sysUserService.selectRolesByUserId(user.getId());
        for (SysRole sysRole : roleList) {
            authorizationInfo.addRole(sysRole.getRole());
            List<SysPermission> permissionList = sysRoleService.selectPermissionsByRoleId(sysRole.getId());
            for (SysPermission sysPermission : permissionList) {
                authorizationInfo.addStringPermission(sysPermission.getPermission());
            }
        }
        return authorizationInfo;
    }

    class MySimpleCredentialsMationInfo extends SimpleCredentialsMatcher {
        @Override
        public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
            return true;
        }
    }
}
