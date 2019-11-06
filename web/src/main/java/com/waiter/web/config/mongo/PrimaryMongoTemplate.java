package com.waiter.web.config.mongo;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @ClassName FirstMongoTemplate
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/23 19:30
 * @Version 1.0
 */
@Configuration
@EnableConfigurationProperties(MultipleMongoProperties.class)
@EnableMongoRepositories(basePackages = "com.waiter.web.dao.primarymongo", mongoTemplateRef = "primaryMongoTemplate")
public class PrimaryMongoTemplate {

}
