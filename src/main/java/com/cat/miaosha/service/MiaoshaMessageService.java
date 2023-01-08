package com.cat.miaosha.service;

import com.cat.miaosha.entity.MiaoshaMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (MiaoshaMessage)表服务接口
 *
 * @author makejava
 * @since 2023-01-08 10:06:10
 */
public interface MiaoshaMessageService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MiaoshaMessage queryById(Long id);

    /**
     * 分页查询
     *
     * @param miaoshaMessage 筛选条件
     * @param pageRequest    分页对象
     * @return 查询结果
     */
    Page<MiaoshaMessage> queryByPage(MiaoshaMessage miaoshaMessage, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param miaoshaMessage 实例对象
     * @return 实例对象
     */
    MiaoshaMessage insert(MiaoshaMessage miaoshaMessage);

    /**
     * 修改数据
     *
     * @param miaoshaMessage 实例对象
     * @return 实例对象
     */
    MiaoshaMessage update(MiaoshaMessage miaoshaMessage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
