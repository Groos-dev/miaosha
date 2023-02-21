package com.cat.miaosha.common;

import com.cat.miaosha.common.contants.ResultStatus;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Mr.xin
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -3948389268046368059L;
    int code;
    String message;
    T data;

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Result<Object> success(){
        return new Result<Object>(ResultStatus.SUECCSS.code, ResultStatus.SUECCSS.message );
    }
    public static <T> Result<T> success(T data){
        Result<T> res = new Result<>(ResultStatus.SUECCSS.code, ResultStatus.SUECCSS.message);
        res.setData(data);
        return res;
    }

    public static <T> Result<T> success(String message,T data){
        Result<T> res = new Result<>(ResultStatus.SUECCSS.code, message);
        res.setData(data);
        return res;
    }

    public static Result<Object> fail(){
        return new Result<Object>(ResultStatus.FAIL.code, ResultStatus.FAIL.message );
    }
    public static <T> Result<T> fail(T data){
        Result<T> res = new Result<>(ResultStatus.FAIL.code, ResultStatus.FAIL.message);
        res.setData(data);
        return res;
    }

    public static <T> Result<T> fail(String message,T data){
        Result<T> res = new Result<>(ResultStatus.FAIL.code, message);
        res.setData(data);
        return res;
    }

    public static <T> Result<T> build(ResultStatus status,T data){
        Result<T> res = new Result<>(status.code, status.message);
        res.setData(data);
        return res;
    }

    public static <T> Result<T> build(int code,String message,T data){
        Result<T> res = new Result<>(code, message);
        res.setData(data);
        return res;
    }


}
