package com.cat.miaosha.controller;

import com.cat.miaosha.entity.OrderInfo;
import com.cat.miaosha.service.OrderInfoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (OrderInfo)表控制层
 *
 * @author makejava
 * @since 2023-01-08 10:06:10
 */
@RestController
@RequestMapping("orderInfo")
public class OrderInfoController {
    /**
     * 服务对象
     */
    @Resource
    private OrderInfoService orderInfoService;

    /**
     * 分页查询
     *
     * @param orderInfo   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<OrderInfo>> queryByPage(OrderInfo orderInfo, PageRequest pageRequest) {
        return ResponseEntity.ok(this.orderInfoService.queryByPage(orderInfo, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<OrderInfo> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.orderInfoService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param orderInfo 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<OrderInfo> add(OrderInfo orderInfo) {
        return ResponseEntity.ok(this.orderInfoService.insert(orderInfo));
    }

    /**
     * 编辑数据
     *
     * @param orderInfo 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<OrderInfo> edit(OrderInfo orderInfo) {
        return ResponseEntity.ok(this.orderInfoService.update(orderInfo));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.orderInfoService.deleteById(id));
    }

}

