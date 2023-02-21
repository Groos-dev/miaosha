package com.cat.miaosha.common.dto.req;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Mr.xin
 */
@Data
@ApiModel
public class GoodsReqDto {

    private String title;

    private Double price;

    private String description;

    private Integer sales;

    private String imgUrl;


}
