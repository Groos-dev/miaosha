package com.cat.miaosha.service.impl;

import com.cat.miaosha.common.dto.req.UserDto;
import com.cat.miaosha.dao.UserDOMapper;
import com.cat.miaosha.dao.UserPasswordDOMapper;
import com.cat.miaosha.entity.UserDO;
import com.cat.miaosha.entity.UserPasswordDO;
import com.cat.miaosha.excption.BusinessException;
import com.cat.miaosha.service.UserService;
import com.cat.miaosha.utils.BeanUtils;
import com.cat.miaosha.utils.IDUtils;
import com.cat.miaosha.utils.MD5Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Mr.xin
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDOMapper userDOMapper;

    @Resource
    private UserPasswordDOMapper userPasswordMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserDto userDto) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDto, userDO);
        long userId = IDUtils.generateId();
        userDO.setId(userId);
        userDOMapper.insert(userDO);

        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setId(IDUtils.generateId());
        userPasswordDO.setUserId(userId);
        userPasswordDO.setEncrptPassword(userDto.getEncrptPassword());
        userPasswordMapper.insertSelective(userPasswordDO);
    }

    @Override
    public UserDO getUserById(Long id) {
        return userDOMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserDO validateLogin(String telephone, String password) throws BusinessException {
        UserDO userDO = userDOMapper.selectByTelphone(telephone);
        if(userDO == null){
            throw new BusinessException(500,"用户不存在,请输入正确的电话号码");
        }
        UserPasswordDO userPasswordDO = userPasswordMapper.selectByUserId(userDO.getId());
        boolean b = userPasswordDO.getEncrptPassword().equals(MD5Utils.encode(password));
        return b ? userDO : null;
    }

    @Override
    public UserDO getUserByPhone(String telephone) {
        return userDOMapper.selectByTelphone(telephone);
    }
}
