package com.cat.miaosha.entity;

import java.io.Serializable;

/**
 * (MiaoshaOrder)实体类
 *
 * @author makejava
 * @since 2023-01-08 10:06:10
 */
public class MiaoshaOrder implements Serializable {
    private static final long serialVersionUID = 951599751533244471L;

    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 商品ID
     */
    private Long goodsId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

}

