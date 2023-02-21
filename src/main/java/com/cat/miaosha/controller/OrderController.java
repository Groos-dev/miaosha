package com.cat.miaosha.controller;

import com.cat.miaosha.common.Result;
import com.cat.miaosha.common.contants.ResultStatus;
import com.cat.miaosha.common.vo.OrderVO;
import com.cat.miaosha.entity.UserDO;
import com.cat.miaosha.excption.BusinessException;
import com.cat.miaosha.service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.cat.miaosha.common.contants.UserConstants.USER_INFO;

/**
 * @author Mr.xin
 */
@RestController
@Slf4j
@RequestMapping("order")
@CrossOrigin(origins = {"*"}, allowCredentials = "true")
public class OrderController {


    @Resource
    private OrderService orderService;

    //封装下单请求
    @PostMapping(value = "/createOrder")
    public Result<Object> createOrder(@NotNull(message = "商品不能为空") @RequestParam(name = "itemId") Long itemId,
                                      @NotNull(message = "数量不能为空") @RequestParam(name = "amount") Integer amount,
                                      @RequestParam(name = "promoId", required = false) Long promoId) throws BusinessException, ExecutionException {

        UserDO userDO = USER_INFO.get();
        if (userDO == null) {
            throw new BusinessException(ResultStatus.LOGIN_EXPIRED);
        }
        Long userId = userDO.getId();
        String orderId = orderService.createOrder(userId, itemId, promoId, amount);
        Map<String, Object> data = new HashMap(1);
        data.put("orderId", orderId);
        return Result.build(ResultStatus.SUECCSS, data);
    }

    @GetMapping("getOrderStatus")
    @ApiOperation(value = "创建订单的回调接口")
    public Result<Object> getOrderStatus(@RequestParam("orederId") String orderId) {
        int status = orderService.getOrderStatus(orderId);
        /**
         *  1 刚创建
         *  2 创建成功
         *  3 创建失败
         */
        switch (status) {
            case 2:
                return Result.build(ResultStatus.ORDER_CREATE_SUCCESS, null);
            case 3:
                return Result.build(ResultStatus.ORDER_CREATE_FAIL, null);
            default:
                return Result.build(ResultStatus.ORDER_CREATING, null);
        }
    }


}
