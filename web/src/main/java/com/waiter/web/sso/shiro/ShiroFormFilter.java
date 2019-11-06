package com.waiter.web.sso.shiro;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @ClassName ShiroFormFilter
 * @Description 自定义的shiro登录表单拦截器，可以通过改写里面的方法来实现一些定制的登录功能
 * @Author lizhihui
 * @Date 2019/8/22 11:52
 * @Version 1.0
 */
public class ShiroFormFilter extends FormAuthenticationFilter {
    /**
     * 改写登录成功的跳转页(默认为先去查找session里面存储的目的页，如果没有找到再去setSuccessUrl设置的页面)
     *
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.getAndClearSavedRequest(request);
        WebUtils.redirectToSavedRequest(request, response, "/success");
        return false;
    }
}
