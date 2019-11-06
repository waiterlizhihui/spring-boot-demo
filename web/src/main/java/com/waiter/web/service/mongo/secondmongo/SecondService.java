package com.waiter.web.service.mongo.secondmongo;

import com.mongodb.DBCursor;
import com.waiter.web.config.mongo.SecondMongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;

/**
 * @ClassName SecondService
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/9/26 10:22
 * @Version 1.0
 */
public class SecondService {
    @Resource(name = "secondaryMongoTemplate")
    MongoTemplate  mongoTemplate;

    public void insert(){
        int start = 100;
//        DBCursor member = mongoTemplate.getCollection("member").find().skip(start).limit(20);
    }
}
