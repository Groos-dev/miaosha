package com.cat.miaosha.controller;

import com.cat.miaosha.entity.MiaoshaOrder;
import com.cat.miaosha.service.MiaoshaOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (MiaoshaOrder)表控制层
 *
 * @author makejava
 * @since 2023-01-08 10:06:10
 */
@RestController
@RequestMapping("miaoshaOrder")
public class MiaoshaOrderController {
    /**
     * 服务对象
     */
    @Resource
    private MiaoshaOrderService miaoshaOrderService;

    /**
     * 分页查询
     *
     * @param miaoshaOrder 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<MiaoshaOrder>> queryByPage(MiaoshaOrder miaoshaOrder, PageRequest pageRequest) {
        return ResponseEntity.ok(this.miaoshaOrderService.queryByPage(miaoshaOrder, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<MiaoshaOrder> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.miaoshaOrderService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param miaoshaOrder 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<MiaoshaOrder> add(MiaoshaOrder miaoshaOrder) {
        return ResponseEntity.ok(this.miaoshaOrderService.insert(miaoshaOrder));
    }

    /**
     * 编辑数据
     *
     * @param miaoshaOrder 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<MiaoshaOrder> edit(MiaoshaOrder miaoshaOrder) {
        return ResponseEntity.ok(this.miaoshaOrderService.update(miaoshaOrder));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.miaoshaOrderService.deleteById(id));
    }

}

