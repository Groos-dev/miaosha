package com.cat.miaosha.entity;

import lombok.Data;

import java.util.Date;


/**
 * @author Mr.xin
 */

@Data
public class PromoDO {
    private Long id;

    private String promoName;

    private Date startDate;

    private Date endDate;

    private Long itemId;

    private Double promoItemPrice;
}