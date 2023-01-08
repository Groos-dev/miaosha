package com.cat.miaosha.service;

import com.cat.miaosha.entity.MiaoshaMessageUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (MiaoshaMessageUser)表服务接口
 *
 * @author makejava
 * @since 2023-01-08 10:06:10
 */
public interface MiaoshaMessageUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MiaoshaMessageUser queryById(Long id);

    /**
     * 分页查询
     *
     * @param miaoshaMessageUser 筛选条件
     * @param pageRequest        分页对象
     * @return 查询结果
     */
    Page<MiaoshaMessageUser> queryByPage(MiaoshaMessageUser miaoshaMessageUser, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param miaoshaMessageUser 实例对象
     * @return 实例对象
     */
    MiaoshaMessageUser insert(MiaoshaMessageUser miaoshaMessageUser);

    /**
     * 修改数据
     *
     * @param miaoshaMessageUser 实例对象
     * @return 实例对象
     */
    MiaoshaMessageUser update(MiaoshaMessageUser miaoshaMessageUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
