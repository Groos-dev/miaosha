package com.cat.miaosha.common.vo;

import com.cat.miaosha.entity.ItemStockDO;
import com.cat.miaosha.entity.PromoDO;
import com.cat.miaosha.service.PromoService;
import lombok.Data;

/**
 * @author Mr.xin
 */
@Data
public class ItemVO {

    private Long id;

    private String title;

    private Double price;

    private String description;

    private Integer sales;

    private String imgUrl;

    private PromoDO promo;
    private Integer stock;
}
