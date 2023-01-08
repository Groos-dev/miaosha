package com.cat.miaosha.service;

import com.cat.miaosha.entity.MiaoshaGoods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (MiaoshaGoods)表服务接口
 *
 * @author makejava
 * @since 2023-01-08 10:06:09
 */
public interface MiaoshaGoodsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MiaoshaGoods queryById(Long id);

    /**
     * 分页查询
     *
     * @param miaoshaGoods 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    Page<MiaoshaGoods> queryByPage(MiaoshaGoods miaoshaGoods, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param miaoshaGoods 实例对象
     * @return 实例对象
     */
    MiaoshaGoods insert(MiaoshaGoods miaoshaGoods);

    /**
     * 修改数据
     *
     * @param miaoshaGoods 实例对象
     * @return 实例对象
     */
    MiaoshaGoods update(MiaoshaGoods miaoshaGoods);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
