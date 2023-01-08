package com.cat.miaosha.controller;


import com.cat.miaosha.common.Result;
import org.springframework.stereotype.Controller;

/**
 * @author Mr.xin
 */
@Controller
public class BaseController {

    public Result<Object> success(){
        return Result.success();
    }
    public Result<Object> success(Object data){
        return Result.success(data);
    }

    public Result<Object> fail(){
        return Result.fail();
    }
    public Result<Object> fail(Object data){
        return Result.fail(data);
    }
}
