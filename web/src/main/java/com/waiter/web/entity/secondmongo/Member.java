package com.waiter.web.entity.secondmongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @ClassName member
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/9/26 10:14
 * @Version 1.0
 */
@Data
@Document(collection = "member")
public class Member implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String objectId;

    @Field("wx_union_id")
    private String wxUnionId;

    @Field("wx_open_id")
    private String wxOpenId;

    @Field("wx_nick_name")
    private String wxNickname;

    @Field("wx_head_img_url")
    private String wxHeadImgUrl;

    @Field("sex")
    private Integer sex;

    @Field("create_date")
    private String createDate;

    @Field("app_secret")
    private String appSecret;

    @Field("vip_start_time")
    private Long vipStartTime;  //vip开始时间

    @Field("vip_end_time")
    private Long vipEndTime;    //vip结束时间

    @Field("status")
    private Integer status;   //0:停用 1：启用

    @Field("rank")
    private Integer rank;//用户级别
}
