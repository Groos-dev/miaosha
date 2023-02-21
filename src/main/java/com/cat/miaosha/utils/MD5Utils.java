package com.cat.miaosha.utils;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Mr.xin
 */
@Slf4j
public class MD5Utils {
    public static String encode(String str)  {
        String encode = str;
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            encode = base64en.encode(md5.digest(str.getBytes("utf-8")));
        }catch (Exception e){
            log.error("e:{},cause:{},message:{}",e.getClass().getSimpleName(),e.getCause(),e.getMessage());
        }
        return  encode;
    }
}
