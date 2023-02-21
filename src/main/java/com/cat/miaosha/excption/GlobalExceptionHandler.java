package com.cat.miaosha.excption;

import cn.hutool.core.thread.ThreadException;
import com.cat.miaosha.common.Result;
import com.cat.miaosha.common.contants.ResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;

/**
 * @author Mr.xin
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result<Object> handleGlobalException(HttpServletRequest servletRequest, Exception e) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("reqPath", servletRequest.getRequestURI());
        if (e instanceof BusinessException) {
            log.error("e:{},message{},path:{},", e.getClass().getSimpleName(), e.getMessage(), servletRequest.getRequestURI());
            StackTraceElement s= e.getStackTrace()[0];
            log.error("\n\n-----------------"+
                    "\n报错文件名:"+s.getFileName()+
                    "\n报错的类："+s.getClassName()+
                    "\n报错方法：："+s.getMethodName()+
                    "\n报错的行："+ s.getLineNumber()+
                    "\n报错的message："+ e.getMessage()+
                    "\n------------------\n\n");
            return Result.build(((BusinessException) e).getCode(), e.getMessage(), map);
        }

        StackTraceElement s= e.getStackTrace()[0];
        log.error("\n\n-----------------"+
                "\n报错文件名:"+s.getFileName()+
                "\n报错的类："+s.getClassName()+
                "\n报错方法：："+s.getMethodName()+
                "\n报错的行："+ s.getLineNumber()+
                "\n报错的message："+ e.getMessage()+
                "\n------------------\n\n");

        log.error("e:{},cause:{},path:{}", e.getClass().getSimpleName(), e.getCause(), servletRequest.getRequestURI());
        return Result.build(500,e.getMessage(), map);
    }

}
