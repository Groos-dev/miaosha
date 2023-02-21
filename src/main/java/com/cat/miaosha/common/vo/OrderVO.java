package com.cat.miaosha.common.vo;

import lombok.Data;



/**
 * @author Mr.xin
 */
@Data
public class OrderVO {
    private String id;

    //购买的用户id
    private Long userId;

    //购买的商品id
    private Long itemId;

    //若非空，则表示是以秒杀商品方式下单
    private Long promoId;

    //购买商品的单价,若promoId非空，则表示秒杀商品价格
    private Double itemPrice;

    //购买数量
    private Integer amount;

    //购买金额,若promoId非空，则表示秒杀商品价格
    private Double orderPrice;


}
