package com.waiter.web.dao.secondmongo;

import com.waiter.web.entity.primarymongo.Info;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface Info2Repository extends MongoRepository<Info, ObjectId> {
    List<Info> findInfosByTinyurlIs(String tinyurl);

    List<Info> findInfosByDomainIs(String domain);

    @Query(value = "{domain : ?0}")
    List<Info> findByDefine(String domain);
}
