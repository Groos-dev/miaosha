package com.cat.miaosha.common.dto.req;

import lombok.Data;

/**
 * @author Mr.xin
 */
@Data
public class UserDto {
    private String name;
    private Byte gender;
    private Integer age;
    private String telphone;
    private String registerMode;
    private String thirdPartyId;

    private String encrptPassword;
    private Long userId;
}
