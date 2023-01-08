package com.cat.miaosha.controller;

import com.cat.miaosha.common.Result;
import com.cat.miaosha.entity.Goods;
import com.cat.miaosha.service.GoodsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Goods)表控制层
 *
 * @author makejava
 * @since 2023-01-08 10:06:09
 */
@RestController
@RequestMapping("goods")
public class GoodsController extends BaseController{
    /**
     * 服务对象
     */
    @Resource
    private GoodsService goodsService;

    /**
     * 分页查询
     *
     * @param goods       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public Result<Page<Goods>> queryByPage(Goods goods, PageRequest pageRequest) {
        return Result.success(this.goodsService.queryByPage(goods, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<Goods> queryById(@PathVariable("id") Long id) {
        return Result.success(goodsService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param goods 实体
     * @return 新增结果
     */
    @PostMapping
    public Result<Goods> add(Goods goods) {
        return Result.success(this.goodsService.insert(goods));
    }

    /**
     * 编辑数据
     *
     * @param goods 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result<Goods> edit(Goods goods) {
        return Result.success(this.goodsService.update(goods));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public Result<Boolean> deleteById(Long id) {
        return Result.success(this.goodsService.deleteById(id));
    }
}

