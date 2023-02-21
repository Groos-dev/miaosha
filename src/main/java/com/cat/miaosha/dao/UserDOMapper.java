package com.cat.miaosha.dao;


import com.cat.miaosha.entity.UserDO;

public interface UserDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectByPrimaryKey(Long id);

    UserDO selectByTelphone(String telphone);
    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);
}