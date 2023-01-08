package com.cat.miaosha.service.impl;

import com.cat.miaosha.entity.OrderInfo;
import com.cat.miaosha.dao.OrderInfoDao;
import com.cat.miaosha.service.OrderInfoService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (OrderInfo)表服务实现类
 *
 * @author makejava
 * @since 2023-01-08 10:06:11
 */
@Service("orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {
    @Resource
    private OrderInfoDao orderInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OrderInfo queryById(Long id) {
        return this.orderInfoDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param orderInfo   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<OrderInfo> queryByPage(OrderInfo orderInfo, PageRequest pageRequest) {
        long total = this.orderInfoDao.count(orderInfo);
        return new PageImpl<>(this.orderInfoDao.queryAllByLimit(orderInfo, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param orderInfo 实例对象
     * @return 实例对象
     */
    @Override
    public OrderInfo insert(OrderInfo orderInfo) {
        this.orderInfoDao.insert(orderInfo);
        return orderInfo;
    }

    /**
     * 修改数据
     *
     * @param orderInfo 实例对象
     * @return 实例对象
     */
    @Override
    public OrderInfo update(OrderInfo orderInfo) {
        this.orderInfoDao.update(orderInfo);
        return this.queryById(orderInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.orderInfoDao.deleteById(id) > 0;
    }
}
