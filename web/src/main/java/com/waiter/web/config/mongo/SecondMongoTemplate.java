package com.waiter.web.config.mongo;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @ClassName SecondMongoTemplate
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/26 19:19
 * @Version 1.0
 */
@Configuration
@EnableConfigurationProperties(MultipleMongoProperties.class)
@EnableMongoRepositories(basePackages = "com.waiter.web.dao.secondmongo", mongoTemplateRef = "secondaryMongoTemplate")
public class SecondMongoTemplate {

}
