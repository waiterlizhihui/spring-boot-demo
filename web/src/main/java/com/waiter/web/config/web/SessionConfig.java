package com.waiter.web.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @ClassName SessionConfig
 * @Description Redis分布式会话配置类
 * @Author lizhihui
 * @Date 2019/8/9 14:03
 * @Version 1.0
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400)//启用redis会话管理,会话失效时间为1小时，可根据实际情况修改
public class SessionConfig {

}
