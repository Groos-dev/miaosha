package com.cat.miaosha.service;

import com.cat.miaosha.entity.MiaoshaOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (MiaoshaOrder)表服务接口
 *
 * @author makejava
 * @since 2023-01-08 10:06:10
 */
public interface MiaoshaOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MiaoshaOrder queryById(Long id);

    /**
     * 分页查询
     *
     * @param miaoshaOrder 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    Page<MiaoshaOrder> queryByPage(MiaoshaOrder miaoshaOrder, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param miaoshaOrder 实例对象
     * @return 实例对象
     */
    MiaoshaOrder insert(MiaoshaOrder miaoshaOrder);

    /**
     * 修改数据
     *
     * @param miaoshaOrder 实例对象
     * @return 实例对象
     */
    MiaoshaOrder update(MiaoshaOrder miaoshaOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
