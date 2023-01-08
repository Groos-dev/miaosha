package com.cat.miaosha.dao;

import com.cat.miaosha.entity.MiaoshaGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (MiaoshaGoods)表数据库访问层
 *
 * @author makejava
 * @since 2023-01-08 10:06:09
 */
public interface MiaoshaGoodsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MiaoshaGoods queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param miaoshaGoods 查询条件
     * @param pageable     分页对象
     * @return 对象列表
     */
    List<MiaoshaGoods> queryAllByLimit(MiaoshaGoods miaoshaGoods, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param miaoshaGoods 查询条件
     * @return 总行数
     */
    long count(MiaoshaGoods miaoshaGoods);

    /**
     * 新增数据
     *
     * @param miaoshaGoods 实例对象
     * @return 影响行数
     */
    int insert(MiaoshaGoods miaoshaGoods);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<MiaoshaGoods> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<MiaoshaGoods> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<MiaoshaGoods> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<MiaoshaGoods> entities);

    /**
     * 修改数据
     *
     * @param miaoshaGoods 实例对象
     * @return 影响行数
     */
    int update(MiaoshaGoods miaoshaGoods);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

