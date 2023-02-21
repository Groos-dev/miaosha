package com.cat.miaosha.entity;

import lombok.Data;

/**
 * @author Mr.xin
 */

@Data
public class ItemDO {



    private Long id;

    private String title;

    private Double price;

    private String description;

    private Integer sales;

    private String imgUrl;
}