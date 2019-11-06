package com.waiter.web.sso.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @ClassName ShiroCredentialsMationInfo
 * @Description 自定义的shiro登录验证方式(目前设置的是shiro自带的HashedCredentialsMatcher)
 * @Author lizhihui
 * @Date 2019/8/22 11:58
 * @Version 1.0
 */
public class ShiroCredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        return true;
    }
}
