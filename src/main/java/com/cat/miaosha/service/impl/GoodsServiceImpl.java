package com.cat.miaosha.service.impl;

import com.cat.miaosha.entity.Goods;
import com.cat.miaosha.dao.GoodsDao;
import com.cat.miaosha.service.GoodsService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Goods)表服务实现类
 *
 * @author makejava
 * @since 2023-01-08 10:06:09
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsDao goodsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Goods queryById(Long id) {
        return this.goodsDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param goods       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Goods> queryByPage(Goods goods, PageRequest pageRequest) {
        long total = this.goodsDao.count(goods);
        return new PageImpl<>(this.goodsDao.queryAllByLimit(goods, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param goods 实例对象
     * @return 实例对象
     */
    @Override
    public Goods insert(Goods goods) {
        this.goodsDao.insert(goods);
        return goods;
    }

    /**
     * 修改数据
     *
     * @param goods 实例对象
     * @return 实例对象
     */
    @Override
    public Goods update(Goods goods) {
        this.goodsDao.update(goods);
        return this.queryById(goods.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.goodsDao.deleteById(id) > 0;
    }
}
