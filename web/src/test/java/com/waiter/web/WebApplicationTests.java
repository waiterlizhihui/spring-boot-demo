package com.waiter.web;

import com.waiter.web.dao.secondmongo.Info2Repository;
import com.waiter.web.entity.primarymongo.Info;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebApplicationTests {
    @Autowired
    com.waiter.web.dao.primarymongo.InfoRepository infoRepository;

    @Autowired
    Info2Repository infoRepository2;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testMongo(){
        List<Info> infoList = infoRepository.findInfosByDomainIs("u3v.cn");
        for(Info info:infoList){
            System.out.println(info.toString());
        }

        List<Info> infoList2 = infoRepository2.findInfosByDomainIs("i6q.cn");
        for(Info info:infoList2){
            System.out.println(info.toString());
        }
    }

}
