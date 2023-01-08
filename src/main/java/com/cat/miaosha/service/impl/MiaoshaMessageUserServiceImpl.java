package com.cat.miaosha.service.impl;

import com.cat.miaosha.entity.MiaoshaMessageUser;
import com.cat.miaosha.dao.MiaoshaMessageUserDao;
import com.cat.miaosha.service.MiaoshaMessageUserService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (MiaoshaMessageUser)表服务实现类
 *
 * @author makejava
 * @since 2023-01-08 10:06:10
 */
@Service("miaoshaMessageUserService")
public class MiaoshaMessageUserServiceImpl implements MiaoshaMessageUserService {
    @Resource
    private MiaoshaMessageUserDao miaoshaMessageUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MiaoshaMessageUser queryById(Long id) {
        return this.miaoshaMessageUserDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param miaoshaMessageUser 筛选条件
     * @param pageRequest        分页对象
     * @return 查询结果
     */
    @Override
    public Page<MiaoshaMessageUser> queryByPage(MiaoshaMessageUser miaoshaMessageUser, PageRequest pageRequest) {
        long total = this.miaoshaMessageUserDao.count(miaoshaMessageUser);
        return new PageImpl<>(this.miaoshaMessageUserDao.queryAllByLimit(miaoshaMessageUser, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param miaoshaMessageUser 实例对象
     * @return 实例对象
     */
    @Override
    public MiaoshaMessageUser insert(MiaoshaMessageUser miaoshaMessageUser) {
        this.miaoshaMessageUserDao.insert(miaoshaMessageUser);
        return miaoshaMessageUser;
    }

    /**
     * 修改数据
     *
     * @param miaoshaMessageUser 实例对象
     * @return 实例对象
     */
    @Override
    public MiaoshaMessageUser update(MiaoshaMessageUser miaoshaMessageUser) {
        this.miaoshaMessageUserDao.update(miaoshaMessageUser);
        return this.queryById(miaoshaMessageUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.miaoshaMessageUserDao.deleteById(id) > 0;
    }
}
