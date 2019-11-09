package com.waiter.web.config.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.waiter.web.sso.shiro.ShiroFormFilter;
import com.waiter.web.sso.shiro.ShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

//import org.crazycake.shiro.RedisCacheManager;
//import org.crazycake.shiro.RedisSessionDAO;

//https://blog.csdn.net/weixin_39781526/article/details/79759762
/**
 * @ClassName ShiroConfig
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/9 14:17
 * @Version 1.0
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //自定义filter
        Map<String, Filter> filterMap = shiroFilterFactoryBean.getFilters();
        filterMap.put("authc", new ShiroFormFilter());

        //shiro拦截设置
        Map<String, String> filterChain = new LinkedHashMap<>();
        filterChain.put("/static/*", "anon");
        filterChain.put("/logout", "logout");
        filterChain.put("/login", "authc");
        filterChain.put("/shiro/*", "authc");
        filterChain.put("/**", "anon");

        //登录地址
        shiroFilterFactoryBean.setLoginUrl("/login");
        //登录成功跳转页
        shiroFilterFactoryBean.setSuccessUrl("/success");
        //未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChain);
        return shiroFilterFactoryBean;
    }

    /**
     * shiro生命周期管理
     *
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 设置加密方式
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1);
        return hashedCredentialsMatcher;
    }


    @Bean
    @DependsOn(value = {"lifecycleBeanPostProcessor", "ShiroRedisCacheManager"})
    public ShiroRealm shiroRealm(RedisTemplate redisTemplate) {
        ShiroRealm shiroRealm = new ShiroRealm();
        //设置密码加密方式
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());

        //设置缓存管理器
        shiroRealm.setCacheManager();
        shiroRealm.setCachingEnabled(true);
        //授权与登录认证禁止缓存
        shiroRealm.setAuthorizationCachingEnabled(false);
        shiroRealm.setAuthenticationCachingEnabled(false);
        return shiroRealm;
    }

    @Bean
    public SecurityManager securityManager(RedisTemplate redisTemplate) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //设置realm
        defaultWebSecurityManager.setRealm(shiroRealm(redisTemplate));
        //设置session管理器，用redis进行管理
        defaultWebSecurityManager.setSessionManager();

        return defaultWebSecurityManager;
    }

    @Bean(name = "ShiroRedisCacheManager")
    public ShiroRe

    /**
     * 开启shiro aop注解支持
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启shiro aop注解支持
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * shiro与thymeleaf整合
     *
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
