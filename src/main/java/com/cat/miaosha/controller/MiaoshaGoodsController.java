package com.cat.miaosha.controller;

import com.cat.miaosha.entity.MiaoshaGoods;
import com.cat.miaosha.service.MiaoshaGoodsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (MiaoshaGoods)表控制层
 *
 * @author makejava
 * @since 2023-01-08 10:06:09
 */
@RestController
@RequestMapping("miaoshaGoods")
public class MiaoshaGoodsController {
    /**
     * 服务对象
     */
    @Resource
    private MiaoshaGoodsService miaoshaGoodsService;

    /**
     * 分页查询
     *
     * @param miaoshaGoods 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<MiaoshaGoods>> queryByPage(MiaoshaGoods miaoshaGoods, PageRequest pageRequest) {
        return ResponseEntity.ok(this.miaoshaGoodsService.queryByPage(miaoshaGoods, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<MiaoshaGoods> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.miaoshaGoodsService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param miaoshaGoods 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<MiaoshaGoods> add(MiaoshaGoods miaoshaGoods) {
        return ResponseEntity.ok(this.miaoshaGoodsService.insert(miaoshaGoods));
    }

    /**
     * 编辑数据
     *
     * @param miaoshaGoods 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<MiaoshaGoods> edit(MiaoshaGoods miaoshaGoods) {
        return ResponseEntity.ok(this.miaoshaGoodsService.update(miaoshaGoods));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.miaoshaGoodsService.deleteById(id));
    }

}

