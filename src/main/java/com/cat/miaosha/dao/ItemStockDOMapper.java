package com.cat.miaosha.dao;

import com.cat.miaosha.entity.ItemStockDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author Mr.xin
 */
public interface ItemStockDOMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ItemStockDO record);

    int insertSelective(ItemStockDO record);


    ItemStockDO selectByPrimaryKey(Integer id);

    ItemStockDO selectByItemId(Long itemId);

    int decreaseStock(@Param("itemId") Long itemId, @Param("amount") Integer amount);

    int updateByPrimaryKeySelective(ItemStockDO record);


    int updateByPrimaryKey(ItemStockDO record);
}