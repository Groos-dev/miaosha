package com.cat.miaosha.controller;

import com.cat.miaosha.entity.MiaoshaMessage;
import com.cat.miaosha.service.MiaoshaMessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (MiaoshaMessage)表控制层
 *
 * @author makejava
 * @since 2023-01-08 10:06:09
 */
@RestController
@RequestMapping("miaoshaMessage")
public class MiaoshaMessageController {
    /**
     * 服务对象
     */
    @Resource
    private MiaoshaMessageService miaoshaMessageService;

    /**
     * 分页查询
     *
     * @param miaoshaMessage 筛选条件
     * @param pageRequest    分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<MiaoshaMessage>> queryByPage(MiaoshaMessage miaoshaMessage, PageRequest pageRequest) {
        return ResponseEntity.ok(this.miaoshaMessageService.queryByPage(miaoshaMessage, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<MiaoshaMessage> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.miaoshaMessageService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param miaoshaMessage 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<MiaoshaMessage> add(MiaoshaMessage miaoshaMessage) {
        return ResponseEntity.ok(this.miaoshaMessageService.insert(miaoshaMessage));
    }

    /**
     * 编辑数据
     *
     * @param miaoshaMessage 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<MiaoshaMessage> edit(MiaoshaMessage miaoshaMessage) {
        return ResponseEntity.ok(this.miaoshaMessageService.update(miaoshaMessage));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.miaoshaMessageService.deleteById(id));
    }

}

