package com.cat.miaosha.common;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Mr.xin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {
    long total;
    int pageSize;
    int pageNum;
    List<T> data;

}
