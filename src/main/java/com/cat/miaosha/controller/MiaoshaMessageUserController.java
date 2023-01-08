package com.cat.miaosha.controller;

import com.cat.miaosha.entity.MiaoshaMessageUser;
import com.cat.miaosha.service.MiaoshaMessageUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (MiaoshaMessageUser)表控制层
 *
 * @author makejava
 * @since 2023-01-08 10:06:10
 */
@RestController
@RequestMapping("miaoshaMessageUser")
public class MiaoshaMessageUserController {
    /**
     * 服务对象
     */
    @Resource
    private MiaoshaMessageUserService miaoshaMessageUserService;

    /**
     * 分页查询
     *
     * @param miaoshaMessageUser 筛选条件
     * @param pageRequest        分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<MiaoshaMessageUser>> queryByPage(MiaoshaMessageUser miaoshaMessageUser, PageRequest pageRequest) {
        return ResponseEntity.ok(this.miaoshaMessageUserService.queryByPage(miaoshaMessageUser, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<MiaoshaMessageUser> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.miaoshaMessageUserService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param miaoshaMessageUser 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<MiaoshaMessageUser> add(MiaoshaMessageUser miaoshaMessageUser) {
        return ResponseEntity.ok(this.miaoshaMessageUserService.insert(miaoshaMessageUser));
    }

    /**
     * 编辑数据
     *
     * @param miaoshaMessageUser 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<MiaoshaMessageUser> edit(MiaoshaMessageUser miaoshaMessageUser) {
        return ResponseEntity.ok(this.miaoshaMessageUserService.update(miaoshaMessageUser));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.miaoshaMessageUserService.deleteById(id));
    }

}

