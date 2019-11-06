package com.waiter.web.entity.primarymongo;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @ClassName Info
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/8/22 19:09
 * @Version 1.0
 */
@Data
@Document(collection = "info")
public class Info {
    /**
     * mongo生成的ID
     */
    @Id
    private ObjectId id;

    /**
     * 域名
     */
    private String domain;

    /**
     * 长网址
     */
    private String longurl;

    /**
     * 长网址MD5
     */
    private String urlmd5;

    /**
     * 生成的短网址
     */
    private String tinyurl;

    /**
     * 显示名称
     */
    private String name;

    /**
     * 生成时间
     */
    private Integer addtime;

    /**
     * 短网址状态类型，0未知404（Info表找不到），1正常，2暂停
     */
    private Integer type;

    /**
     * 登录用户的mid，未登录时为0
     */
    private String mid;

    /**
     * 用户cookie_id
     */
    @Field("cookie_id")
    private String cookieId;

    /**
     * 获取生成者的ip
     */
    private String ip;

    /**
     * 生成密码短网址需要的用户输入的密码
     */
    private String pwd;

    /**
     * 应用id（生成方式：0单独生成，1多渠道生成，2自定义短网址，3批量生成。4API生成，5落地页生成，6合作伙伴用户生成，7淘宝客生成，8密码生成）
     */
    private Integer urltype;

    /**
     * 有效期类型（1:7天，2:30天，3:90天，4:180天，5:365天，6:永久）
     */
    @Field("expire_type")
    private Integer expireType;

    /**
     * 过期时间
     */
    @Field("expire_date")
    private Integer expireDate;

    /**
     * 最后一次访问时间
     */
    @Field("last_visit_time")
    private Integer lastVisitTime;

    /**
     * 网址不安全标志，0安全，1不安全
     */
    @Field("unsafe_flag")
    private Integer unsafeFlag;

    /**
     * 是否允许嵌套iframe,0未知,1允许，2不允许，默认0未知
     */
    @Field("allow_iframe")
    private Integer allowIframe;


    /**
     * 短网址对应的长网址标签
     */
    private String tag;


    /**
     * 短网址对应的长网址关键字
     */
    private String keywords;

    @Field("cache_duration")
    private Integer cacheDuration;

    /**
     * 备注
     */
    private String beizhu;


    /**
     * 弱防红: 0否, 1是
     */
    @Field("red_type")
    private Integer redType;


    /**
     * 是否被删除：0代表未被删除，1代表已删除
     */
    @Field("del_status")
    private Integer delStatus;
}
