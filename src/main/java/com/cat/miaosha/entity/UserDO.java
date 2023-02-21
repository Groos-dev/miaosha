package com.cat.miaosha.entity;

import lombok.Data;

/**
 * @author Mr.xin
 */
@Data
public class UserDO {

    private Long id;


    private String name;


    private Byte gender;

    private Integer age;


    private String telphone;


    private String registerMode;

    private String thirdPartyId;


}