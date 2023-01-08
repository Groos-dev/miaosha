package com.cat.miaosha.dao;

import com.cat.miaosha.entity.MiaoshaOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (MiaoshaOrder)表数据库访问层
 *
 * @author makejava
 * @since 2023-01-08 10:06:10
 */
public interface MiaoshaOrderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MiaoshaOrder queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param miaoshaOrder 查询条件
     * @param pageable     分页对象
     * @return 对象列表
     */
    List<MiaoshaOrder> queryAllByLimit(MiaoshaOrder miaoshaOrder, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param miaoshaOrder 查询条件
     * @return 总行数
     */
    long count(MiaoshaOrder miaoshaOrder);

    /**
     * 新增数据
     *
     * @param miaoshaOrder 实例对象
     * @return 影响行数
     */
    int insert(MiaoshaOrder miaoshaOrder);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<MiaoshaOrder> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<MiaoshaOrder> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<MiaoshaOrder> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<MiaoshaOrder> entities);

    /**
     * 修改数据
     *
     * @param miaoshaOrder 实例对象
     * @return 影响行数
     */
    int update(MiaoshaOrder miaoshaOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

