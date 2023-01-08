package com.cat.miaosha.service;

import com.cat.miaosha.entity.OrderInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (OrderInfo)表服务接口
 *
 * @author makejava
 * @since 2023-01-08 10:06:11
 */
public interface OrderInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OrderInfo queryById(Long id);

    /**
     * 分页查询
     *
     * @param orderInfo   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<OrderInfo> queryByPage(OrderInfo orderInfo, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param orderInfo 实例对象
     * @return 实例对象
     */
    OrderInfo insert(OrderInfo orderInfo);

    /**
     * 修改数据
     *
     * @param orderInfo 实例对象
     * @return 实例对象
     */
    OrderInfo update(OrderInfo orderInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
