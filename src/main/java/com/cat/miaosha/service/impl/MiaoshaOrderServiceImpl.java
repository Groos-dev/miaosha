package com.cat.miaosha.service.impl;

import com.cat.miaosha.entity.MiaoshaOrder;
import com.cat.miaosha.dao.MiaoshaOrderDao;
import com.cat.miaosha.service.MiaoshaOrderService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (MiaoshaOrder)表服务实现类
 *
 * @author makejava
 * @since 2023-01-08 10:06:10
 */
@Service("miaoshaOrderService")
public class MiaoshaOrderServiceImpl implements MiaoshaOrderService {
    @Resource
    private MiaoshaOrderDao miaoshaOrderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MiaoshaOrder queryById(Long id) {
        return this.miaoshaOrderDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param miaoshaOrder 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @Override
    public Page<MiaoshaOrder> queryByPage(MiaoshaOrder miaoshaOrder, PageRequest pageRequest) {
        long total = this.miaoshaOrderDao.count(miaoshaOrder);
        return new PageImpl<>(this.miaoshaOrderDao.queryAllByLimit(miaoshaOrder, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param miaoshaOrder 实例对象
     * @return 实例对象
     */
    @Override
    public MiaoshaOrder insert(MiaoshaOrder miaoshaOrder) {
        this.miaoshaOrderDao.insert(miaoshaOrder);
        return miaoshaOrder;
    }

    /**
     * 修改数据
     *
     * @param miaoshaOrder 实例对象
     * @return 实例对象
     */
    @Override
    public MiaoshaOrder update(MiaoshaOrder miaoshaOrder) {
        this.miaoshaOrderDao.update(miaoshaOrder);
        return this.queryById(miaoshaOrder.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.miaoshaOrderDao.deleteById(id) > 0;
    }
}
