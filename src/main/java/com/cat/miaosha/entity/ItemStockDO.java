package com.cat.miaosha.entity;

import lombok.Data;

/**
 * @author Mr.xin
 */
@Data
public class ItemStockDO {

    private Long id;

    private Integer stock;

    private Long itemId;
}