package com.cat.miaosha.entity;

import lombok.Data;

/**
 * @author Mr.xin
 */
@Data
public class StockLogDO {

    private String stockLogId;

    private Long itemId;

    private Integer amount;


    /**
     * 1 表示创建 2表示订单创建成功 3表示订单创建失败
     */

    private Integer status;


}