package com.waiter.web.config.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * @ClassName MongoConfig
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/23 11:18
 * @Version 1.0
 */
@Configuration
public class MultiMongoConfig {
    @Autowired
    private MultipleMongoProperties mongoProperties;

    @Bean
    public MongoMappingContext mongoMappingContext() {
        MongoMappingContext mappingContext = new MongoMappingContext();
        return mappingContext;
    }

    /**
     * 去除插入时的_class
     *
     * @return
     */
    @Bean
    public MappingMongoConverter mappingMongoConverter1() {
        DefaultDbRefResolver defaultDbRefResolver = new DefaultDbRefResolver(primaryFactory(this.mongoProperties.getPrimary()));
        MappingMongoConverter converter = new MappingMongoConverter(defaultDbRefResolver, this.mongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter2() {
        DefaultDbRefResolver defaultDbRefResolver = new DefaultDbRefResolver(primaryFactory(this.mongoProperties.getSecondary()));
        MappingMongoConverter converter = new MappingMongoConverter(defaultDbRefResolver, this.mongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }

    @Primary
    @Bean(name = "primaryMongoTemplate")
    public MongoTemplate primaryMongoTemplate() {
        return new MongoTemplate(primaryFactory(this.mongoProperties.getPrimary()), mappingMongoConverter1());

    }

    @Primary
    @Bean(name = "secondaryMongoTemplate")
    public MongoTemplate secondaryMongoTemplate() {
        return new MongoTemplate(secondaryFactory(this.mongoProperties.getSecondary()), mappingMongoConverter2());
    }

    @Bean
    @Primary
    public MongoDbFactory primaryFactory(final MongoProperties mongo) {
        MongoClient client = new MongoClient(new MongoClientURI(mongoProperties.getPrimary().getUri()));
        return new SimpleMongoDbFactory(client, mongoProperties.getPrimary().getDatabase());
    }

    @Bean
    public MongoDbFactory secondaryFactory(final MongoProperties mongo) {
        MongoClient client = new MongoClient(new MongoClientURI(mongoProperties.getSecondary().getUri()));
        return new SimpleMongoDbFactory(client, mongoProperties.getSecondary().getDatabase());
    }
}
