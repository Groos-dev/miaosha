package com.cat.miaosha;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cat.miaosha.common.Page;
import com.cat.miaosha.common.request.PageRequest;
import com.cat.miaosha.common.vo.ItemVO;
import com.cat.miaosha.entity.ItemDO;
import com.cat.miaosha.entity.UserDO;
import com.cat.miaosha.service.ItemService;
import com.cat.miaosha.utils.JwtUtils;
import com.cat.miaosha.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Results;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ApplicationTest {


    @Resource
    private ItemService itemService;
    @Test
    public void testJwt() {
        HashMap<String, Object> payload = new HashMap<>();
        String token = JwtUtils.createToken(1000L,60 * 24);
        System.out.println(token);

    }

    @Test
    public void verifyToken(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NzM1MjAzNTIsInVzZXJJZCI6IjAwMDEifQ.97gu5CHcrUTMNc4dmRdBgdfeOUSLU7O0gEyixXqd0KE";
        DecodedJWT jwt = null;
        try {
            Long userId = JwtUtils.verifyToken(token);
        }catch (TokenExpiredException e){
            System.out.println(e.getClass().getSimpleName());
        }
        System.out.println(jwt);
    }
    @Test
    public void testRedisUtils(){
        RedisUtils.set("test",null,3, TimeUnit.SECONDS);
        UserDO test = RedisUtils.get("test");
        System.out.println(test);
    }

    @Test
    public void stockLoadInRedis(){
        PageRequest page = new PageRequest();
        page.setPageNum(1);
        page.setPageSize(1000);
        Page<ItemVO> itemVOPage = itemService.goodsList(new ItemDO(), page);
        List<ItemVO> data = itemVOPage.getData();
        for(ItemVO item : data){
            RedisUtils.set("stock_" + item.getId(),item.getStock());
        }
    }

    @Test
    public void testRedisUtilsDcr(){
       RedisUtils.set("test",1);
        Long test = RedisUtils.decr("test", 1);
//        Integer test = RedisUtils.get("test");
        System.out.println(test);

    }
}
