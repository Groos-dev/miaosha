package com.cat.miaosha.dao;


import com.cat.miaosha.entity.PromoDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PromoDOMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(PromoDO record);

    int insertSelective(PromoDO record);

    PromoDO selectByPrimaryKey(Integer id);

    PromoDO selectByItemId(Long itemId);

    int updateByPrimaryKeySelective(PromoDO record);

    int updateByPrimaryKey(PromoDO record);
}