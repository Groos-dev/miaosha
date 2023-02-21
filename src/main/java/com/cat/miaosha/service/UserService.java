package com.cat.miaosha.service;

import com.cat.miaosha.common.dto.req.UserDto;
import com.cat.miaosha.entity.UserDO;
import com.cat.miaosha.excption.BusinessException;

/**
 * @author Mr.xin
 */
public interface UserService {
    void register(UserDto userDto);

    UserDO getUserById(Long id);

    UserDO validateLogin(String telephone, String password) throws BusinessException;

    UserDO getUserByPhone(String telephone);
}
