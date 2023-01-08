package com.cat.miaosha.service.impl;

import com.cat.miaosha.entity.MiaoshaMessage;
import com.cat.miaosha.dao.MiaoshaMessageDao;
import com.cat.miaosha.service.MiaoshaMessageService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (MiaoshaMessage)表服务实现类
 *
 * @author makejava
 * @since 2023-01-08 10:06:10
 */
@Service("miaoshaMessageService")
public class MiaoshaMessageServiceImpl implements MiaoshaMessageService {
    @Resource
    private MiaoshaMessageDao miaoshaMessageDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MiaoshaMessage queryById(Long id) {
        return this.miaoshaMessageDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param miaoshaMessage 筛选条件
     * @param pageRequest    分页对象
     * @return 查询结果
     */
    @Override
    public Page<MiaoshaMessage> queryByPage(MiaoshaMessage miaoshaMessage, PageRequest pageRequest) {
        long total = this.miaoshaMessageDao.count(miaoshaMessage);
        return new PageImpl<>(this.miaoshaMessageDao.queryAllByLimit(miaoshaMessage, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param miaoshaMessage 实例对象
     * @return 实例对象
     */
    @Override
    public MiaoshaMessage insert(MiaoshaMessage miaoshaMessage) {
        this.miaoshaMessageDao.insert(miaoshaMessage);
        return miaoshaMessage;
    }

    /**
     * 修改数据
     *
     * @param miaoshaMessage 实例对象
     * @return 实例对象
     */
    @Override
    public MiaoshaMessage update(MiaoshaMessage miaoshaMessage) {
        this.miaoshaMessageDao.update(miaoshaMessage);
        return this.queryById(miaoshaMessage.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.miaoshaMessageDao.deleteById(id) > 0;
    }
}
