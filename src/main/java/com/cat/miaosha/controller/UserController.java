package com.cat.miaosha.controller;

import com.cat.miaosha.common.Result;
import com.cat.miaosha.common.annotations.Access;
import com.cat.miaosha.common.dto.req.UserDto;
import com.cat.miaosha.common.vo.UserVO;
import com.cat.miaosha.entity.UserDO;
import com.cat.miaosha.excption.BusinessException;
import com.cat.miaosha.service.UserService;
import com.cat.miaosha.utils.BeanUtils;
import com.cat.miaosha.utils.JwtUtils;
import com.cat.miaosha.utils.MD5Utils;
import com.cat.miaosha.utils.RedisUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.cat.miaosha.common.contants.UserConstants.USER_INFO;

/**
 * @author Mr.xin
 */
@RestController()
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    private static final String OPT_PREFIX = "opt_";
    private static final String USER_PREFIX = "user_";


    @ApiOperation("注册接口")
    @PostMapping(value = "/register")
    @Access
    public Result<Object> register(@RequestParam(name = "telephone") String telephone,
                                   @RequestParam(name = "otpCode") String otpCode,
                                   @RequestParam(name = "name") String name,
                                   @RequestParam(name = "gender") Integer gender,
                                   @RequestParam(name = "age") Integer age,
                                   @RequestParam(name = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

        if (otpCode == null) {
            throw new BusinessException(500, "验证码不能为空");
        }
        String opt = RedisUtils.get(OPT_PREFIX + telephone);
        if (!otpCode.equals(opt)) {
            throw new BusinessException(500, "验证码错误");
        }

        UserDO user = userService.getUserByPhone(telephone);
        if (user != null) {
            throw new BusinessException(500, "用户已经注册");
        }
        UserDto userDto = new UserDto();

        userDto.setName(name);
        userDto.setTelphone(telephone);
        userDto.setGender(new Byte(String.valueOf(gender.intValue())));
        userDto.setAge(age);
        userDto.setRegisterMode("byphone");
        userDto.setEncrptPassword(MD5Utils.encode(password));

        userService.register(userDto);
        return Result.build(200, "注册成功", null);
    }


    @ApiOperation("用户短信接口")
    @Access
    @GetMapping(value = "/getOtp")
    public Result<Object> getOtp(@NotNull(message = "用户电话不能为空") @RequestParam(name = "telephone") String telephone) {
        //模拟验证码
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 10000;
        String otpCode = String.valueOf(randomInt);

        RedisUtils.set(OPT_PREFIX + telephone, otpCode, 60, TimeUnit.SECONDS);

        //将OTP验证码通过短信通道发送给用户,省略
        log.info("telephone = " + telephone + " & otpCode = " + otpCode);
        return Result.build(200, "验证码已发送", null);
    }


    @GetMapping("/get")
    public Result<UserVO> getUser(@RequestParam(name = "id") @NotNull(message = "用户Id不能为空") Long id) throws BusinessException {
        // 从treadLocal取
        UserDO userDO
                = USER_INFO.get();
        if (userDO != null) {
            log.info("user in threadLocal : {}", userDO.toString());
        }


        if (userDO == null) {
            userDO = userService.getUserById(id);
        }

        //若获取的对应用户信息不存在
        if (userDO == null) {
            throw new BusinessException(200, "用户不存在");
        }

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDO, userVO);

        //返回通用对象
        return Result.success(userVO);
    }


    @ApiOperation("用户登录")
    @PostMapping(value = "/login")
    @Access
    public Result<Object> login(@NotNull(message = "用户电话不能为空") @RequestParam(name = "telephone") String telephone,
                                @NotNull(message = "用户密码不能为空") @RequestParam(name = "password") String password) throws BusinessException {

        //用户登陆服务,用来校验用户登陆是否合法
        UserDO userDO = userService.validateLogin(telephone, password);

        if (userDO == null) {
            throw new BusinessException(500, "密码错误");
        }
        RedisUtils.set(USER_PREFIX + userDO.getId(), userDO, 60, TimeUnit.MINUTES);
        log.info("userId:{}", userDO.getId());
        String token = JwtUtils.createToken(userDO.getId(), 24 * 60);
        Map<String, Object> data = new HashMap<>(4);
        data.put("userId", userDO.getId());
        data.put("header", "token");
        data.put("token", token);
        return Result.build(200, "登录成功", data);

    }


}

