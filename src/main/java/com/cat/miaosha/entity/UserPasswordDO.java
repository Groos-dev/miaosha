package com.cat.miaosha.entity;

import lombok.Data;

/**
 * @author Mr.xin
 */
@Data
public class UserPasswordDO {

    private Long id;

    private String encrptPassword;

    private Long userId;

}