package com.cat.miaosha.common;

/**
 * @author Mr.xin
 */
public enum ResultStatus {

    SUECCSS(200,"成功"),
    FAIL(500, "失败");

    public Integer code;
    public String message;

    private ResultStatus(Integer code, String message){
        this.code = code;
        this.message = message;
    }



}
