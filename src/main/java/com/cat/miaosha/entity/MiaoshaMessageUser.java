package com.cat.miaosha.entity;

import java.io.Serializable;

/**
 * (MiaoshaMessageUser)实体类
 *
 * @author makejava
 * @since 2023-01-08 10:06:10
 */
public class MiaoshaMessageUser implements Serializable {
    private static final long serialVersionUID = -21793010588455828L;

    private Long id;

    private Long userid;

    private Long messageid;

    private Integer goodid;

    private Integer orderid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getMessageid() {
        return messageid;
    }

    public void setMessageid(Long messageid) {
        this.messageid = messageid;
    }

    public Integer getGoodid() {
        return goodid;
    }

    public void setGoodid(Integer goodid) {
        this.goodid = goodid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

}

