package com.cat.miaosha.entity;

import lombok.Data;

/**
 * @author Mr.xin
 */

@Data
public class OrderDO {

    private String id;

    private Long userId;

    private Long itemId;

    private Double itemPrice;

    private Integer amount;

    private Double orderPrice;

    private Long promoId;

    /**
     * 1、默认状态
     * 2、创建成功
     * 3、创建失败
     */
    private Integer status;


}