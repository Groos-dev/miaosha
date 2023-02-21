package com.cat.miaosha.dao;


import com.cat.miaosha.entity.UserPasswordDO;

public interface UserPasswordDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPasswordDO record);

    int insertSelective(UserPasswordDO record);

    UserPasswordDO selectByPrimaryKey(Long id);

    UserPasswordDO selectByUserId(Long userId);
    int updateByPrimaryKeySelective(UserPasswordDO record);

    int updateByPrimaryKey(UserPasswordDO record);
}