package com.waiter.web.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName MyWebMvcConfigAdapter
 * @Description 额外增加一些spring mvc的配置
 * 注意：重写的时候必须继承WebMvcConfigurer接口而不是WebMvcConfigurationSupport，如果继承WebMvcConfigurationSupport会导致spring boot里面的默认配置全部失效
 * @Author lizhihui
 * @Date 2019/3/18 19:02
 * @Version 1.0
 */
@Configuration
public class MyWebMvcConfigAdapter implements WebMvcConfigurer {
    /**
     * 配置请求对应的页面
     * @param registry
     */
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/home").setViewName("home");
//    }

    /**
     * 开启指定后缀拦截
     * @param configurer
     */
//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer){
//        configurer.setUseSuffixPatternMatch(true).setUseTrailingSlashMatch(true);
//    }

    /**
     * 设置拦截规则
     * @param dispatcherServlet
     * @return
     */
//    @Bean
//    public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet){
//        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet);
//        servletRegistrationBean.addUrlMappings("*.do");
//        return servletRegistrationBean;
//    }

    /**
     * 加载自定义的拦截器
     * @param registry
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**");
//    }
}
