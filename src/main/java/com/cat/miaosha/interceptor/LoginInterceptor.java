package com.cat.miaosha.interceptor;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cat.miaosha.common.annotations.Access;
import com.cat.miaosha.common.contants.ResultStatus;
import com.cat.miaosha.dao.UserDOMapper;
import com.cat.miaosha.entity.UserDO;
import com.cat.miaosha.excption.BusinessException;
import com.cat.miaosha.utils.JwtUtils;
import com.cat.miaosha.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

import static com.cat.miaosha.common.contants.UserConstants.USER_INFO;
import static com.cat.miaosha.common.contants.UserConstants.USER_PREFIX;

/**
 * @author Mr.xin
 */
@Component("loginInterceptor")
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {


    @Resource
    private UserDOMapper userDOMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserDO userDO = parseToken(request);
        USER_INFO.set(userDO);
        log.info(request.getRequestURI());
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            // 检查类上有没有access注解
            Access access = hm.getClass().getAnnotation(Access.class);
            if (access != null) {
                return true;
            }
            // 检查方法上有没有access注解
            access = hm.getMethodAnnotation(Access.class);
            if (access != null) {
                return true;
            }
            if(USER_INFO.get() == null){
               throw new BusinessException(ResultStatus.LOGIN_EXPIRED);
            }
        }


        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        USER_INFO.remove();
    }
    private UserDO parseToken(HttpServletRequest request){
        String token = request.getHeader("token");

        Long id = null;
        try {
            // TODO: 2023/1/13 处理token续期问题
            id = JwtUtils.verifyToken(token);
        } catch (JWTVerificationException e) {
            log.error("token parse error: {}",e.getMessage());
            return null;
        }
        assert id != null;
        UserDO userDO = RedisUtils.get(USER_PREFIX + id);
        if (userDO == null) {
            userDO = userDOMapper.selectByPrimaryKey(id);
            // TODO: 2023/1/13 缓存时间应该同一配置
            RedisUtils.set(USER_PREFIX + id, userDO, 1, TimeUnit.HOURS);
        }
        // 将用户信息放在threadLocal中
        return userDO;
    }
}
