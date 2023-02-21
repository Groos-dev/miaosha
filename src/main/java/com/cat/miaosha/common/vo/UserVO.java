package com.cat.miaosha.common.vo;

import lombok.Data;

/**
 * @author Mr.xin
 */
@Data
public class UserVO {
    private Long id;
    private String name;
    private Byte gender;
    private Integer age;
    private String telphone;
    private String registerMode;
    private String thirdPartyId;
}
