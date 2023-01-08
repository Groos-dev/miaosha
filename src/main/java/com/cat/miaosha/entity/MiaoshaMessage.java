package com.cat.miaosha.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (MiaoshaMessage)实体类
 *
 * @author makejava
 * @since 2023-01-08 10:06:09
 */
public class MiaoshaMessage implements Serializable {
    private static final long serialVersionUID = -70638339860871375L;
    /**
     * 消息主键
     */
    private Long id;
    /**
     * 分布式id
     */
    private Long messageid;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 1 有效 2 失效
     */
    private Integer status;
    /**
     * 结束时间
     */
    private Date overTime;
    /**
     * 0 秒杀消息 1 购买消息 2 推送消息
     */
    private Integer messageType;
    /**
     * 发送类型 0 app 1 pc 2 ios
     */
    private Integer sendType;
    /**
     * 商品名称
     */
    private String goodName;
    /**
     * 商品价格
     */
    private Double price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMessageid() {
        return messageid;
    }

    public void setMessageid(Long messageid) {
        this.messageid = messageid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}

