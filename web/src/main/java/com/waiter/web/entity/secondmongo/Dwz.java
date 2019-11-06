package com.waiter.web.entity.secondmongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @ClassName Dwz
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/9/26 10:17
 * @Version 1.0
 */
@Data
public class Dwz implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    private String _id;

    /**
     * mid
     */
    private String member_id;

    /**
     * 长网址
     */
    private String long_url;

    /**
     * 长网址md5
     */
    private String url_md5;

    /**
     * 短网址串
     */
    private String tiny_url;

    /**
     * 应用id（生成方式：0单独生成，1多渠道生成，2自定义短网址，3批量生成。4API生成，5落地页生成，6合作伙伴用户生成，7淘宝客生成，8密码生成）
     */
    private Integer app_type;


    /**
     * 弱防红：0否, 1是
     */
    private Integer red_type;


    /**
     * 是否被删除：0代表未被删除，1代表已删除
     */
    private Integer del_status;


    /**
     * 名称
     */
    private String name;

    /**
     * 短网址状态类型，0未知404（Info表找不到），1正常，2暂停
     */
    private Integer type;

    /**
     * APP落地页ios地址
     */
    private String url_ios;

    /**
     * APP落地页安卓地址
     */
    private String url_android;

    /**
     * APP落地页其他环境地址
     */
    private String url_other;

    /**
     * APP落地页图片链接
     */
    private String image;

    /**
     * APP落地页热区
     * "375,238,324,94,0,0"
     */
    private String pic_area;

    /**
     * 生成时间
     * 2018-09-20 17:06:21
     */
    private String create_date;

    /**
     * 生成时间戳（精确到秒）
     */
    private Integer create_date_time;

    /**
     * 有效期类型（1:7天，2:30天，3:90天，4:180天，5:365天，6:永久）
     */
    private Integer expire_type;

    /**
     * 过期时间
     */
    private Integer expire_date;

    /**
     * 多渠道生成存放data
     */
    private String data;

    /**
     * 短网址密码
     */
    private String pwd;

    /**
     * 域名
     */
    private String domain;

    /**
     * t.cn短网址
     */
    private String tiny_url_tcn;
}
