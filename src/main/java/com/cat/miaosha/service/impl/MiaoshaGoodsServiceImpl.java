package com.cat.miaosha.service.impl;

import com.cat.miaosha.entity.MiaoshaGoods;
import com.cat.miaosha.dao.MiaoshaGoodsDao;
import com.cat.miaosha.service.MiaoshaGoodsService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (MiaoshaGoods)表服务实现类
 *
 * @author makejava
 * @since 2023-01-08 10:06:09
 */
@Service("miaoshaGoodsService")
public class MiaoshaGoodsServiceImpl implements MiaoshaGoodsService {
    @Resource
    private MiaoshaGoodsDao miaoshaGoodsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MiaoshaGoods queryById(Long id) {
        return this.miaoshaGoodsDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param miaoshaGoods 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @Override
    public Page<MiaoshaGoods> queryByPage(MiaoshaGoods miaoshaGoods, PageRequest pageRequest) {
        long total = this.miaoshaGoodsDao.count(miaoshaGoods);
        return new PageImpl<>(this.miaoshaGoodsDao.queryAllByLimit(miaoshaGoods, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param miaoshaGoods 实例对象
     * @return 实例对象
     */
    @Override
    public MiaoshaGoods insert(MiaoshaGoods miaoshaGoods) {
        this.miaoshaGoodsDao.insert(miaoshaGoods);
        return miaoshaGoods;
    }

    /**
     * 修改数据
     *
     * @param miaoshaGoods 实例对象
     * @return 实例对象
     */
    @Override
    public MiaoshaGoods update(MiaoshaGoods miaoshaGoods) {
        this.miaoshaGoodsDao.update(miaoshaGoods);
        return this.queryById(miaoshaGoods.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.miaoshaGoodsDao.deleteById(id) > 0;
    }
}
