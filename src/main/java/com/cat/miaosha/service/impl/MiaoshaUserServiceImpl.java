package com.cat.miaosha.service.impl;

import com.cat.miaosha.entity.MiaoshaUser;
import com.cat.miaosha.dao.MiaoshaUserDao;
import com.cat.miaosha.service.MiaoshaUserService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (MiaoshaUser)表服务实现类
 *
 * @author makejava
 * @since 2023-01-08 10:06:10
 */
@Service("miaoshaUserService")
public class MiaoshaUserServiceImpl implements MiaoshaUserService {
    @Resource
    private MiaoshaUserDao miaoshaUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MiaoshaUser queryById(Long id) {
        return this.miaoshaUserDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param miaoshaUser 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<MiaoshaUser> queryByPage(MiaoshaUser miaoshaUser, PageRequest pageRequest) {
        long total = this.miaoshaUserDao.count(miaoshaUser);
        return new PageImpl<>(this.miaoshaUserDao.queryAllByLimit(miaoshaUser, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param miaoshaUser 实例对象
     * @return 实例对象
     */
    @Override
    public MiaoshaUser insert(MiaoshaUser miaoshaUser) {
        this.miaoshaUserDao.insert(miaoshaUser);
        return miaoshaUser;
    }

    /**
     * 修改数据
     *
     * @param miaoshaUser 实例对象
     * @return 实例对象
     */
    @Override
    public MiaoshaUser update(MiaoshaUser miaoshaUser) {
        this.miaoshaUserDao.update(miaoshaUser);
        return this.queryById(miaoshaUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.miaoshaUserDao.deleteById(id) > 0;
    }
}
