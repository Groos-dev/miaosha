package com.cat.miaosha.dao;


import com.cat.miaosha.common.request.PageRequest;
import com.cat.miaosha.entity.ItemDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author Mr.xin
 */
public interface ItemDOMapper {

    List<ItemDO> listItem();

    int deleteByPrimaryKey(Long id);

    int insert(ItemDO record);


    int insertSelective(ItemDO record);


    ItemDO selectByPrimaryKey(Long id);


    int updateByPrimaryKeySelective(ItemDO record);


    int updateByPrimaryKey(ItemDO record);
    int increaseSales(@Param("id")Long id,@Param("amount")Integer amount);

    List<ItemDO> selectListAll(@Param("item") ItemDO goods,@Param("page") PageRequest pageRequest);

    int selectItemCount(@Param("item") ItemDO itemDO);
}