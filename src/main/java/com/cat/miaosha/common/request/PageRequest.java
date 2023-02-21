package com.cat.miaosha.common.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author Mr.xin
 */
@Getter
@Setter
@ApiModel
public class PageRequest {
    @NotNull(message = "pageNum不能为空")
    Integer pageNum;
    @NotNull(message = "pageSize不能为空")
    Integer pageSize;
}
