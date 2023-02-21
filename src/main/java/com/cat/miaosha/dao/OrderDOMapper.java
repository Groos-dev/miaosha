package com.cat.miaosha.dao;


import com.cat.miaosha.entity.OrderDO;
import org.apache.ibatis.annotations.Param;

public interface OrderDOMapper {

     int selectOrderStatusById(@Param("id") String orderId);

    int deleteByPrimaryKey(String id);


    int insert(OrderDO record);

    int insertSelective(OrderDO record);

    OrderDO selectByPrimaryKey(String id);


    int updateByPrimaryKeySelective(OrderDO record);

    int updateByPrimaryKey(OrderDO record);
}