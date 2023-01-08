package com.cat.miaosha.service;

import com.cat.miaosha.entity.MiaoshaUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (MiaoshaUser)表服务接口
 *
 * @author makejava
 * @since 2023-01-08 10:06:10
 */
public interface MiaoshaUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MiaoshaUser queryById(Long id);

    /**
     * 分页查询
     *
     * @param miaoshaUser 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<MiaoshaUser> queryByPage(MiaoshaUser miaoshaUser, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param miaoshaUser 实例对象
     * @return 实例对象
     */
    MiaoshaUser insert(MiaoshaUser miaoshaUser);

    /**
     * 修改数据
     *
     * @param miaoshaUser 实例对象
     * @return 实例对象
     */
    MiaoshaUser update(MiaoshaUser miaoshaUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
