package com.cat.miaosha.controller;

import com.cat.miaosha.common.Page;
import com.cat.miaosha.common.Result;
import com.cat.miaosha.common.annotations.Access;
import com.cat.miaosha.common.annotations.RateLimiter;
import com.cat.miaosha.common.dto.req.GoodsReqDto;
import com.cat.miaosha.common.request.PageRequest;
import com.cat.miaosha.common.vo.ItemVO;
import com.cat.miaosha.entity.ItemDO;
import com.cat.miaosha.entity.PromoDO;
import com.cat.miaosha.entity.UserDO;
import com.cat.miaosha.excption.BusinessException;
import com.cat.miaosha.service.ItemService;
import com.cat.miaosha.service.PromoService;
import com.cat.miaosha.utils.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Mr.xin
 */
@RestController
@RequestMapping("item")
@CrossOrigin("*")
public class ItemController {

    @Resource
    private ItemService itemService;

    @Resource
    private PromoService promoService;

    @PostMapping("/create")
    public Result<Object> createGoods(@RequestBody GoodsReqDto goodsReqDto) {
        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(goodsReqDto, itemDO);
        boolean res = itemService.createItem(itemDO);
        return res ? Result.success("添加成功", null) : Result.fail("添加失败", null);
    }


    @GetMapping("/goodsDetail/{id}")
    @Access
    @RateLimiter
    public Result<ItemVO> getGoodsDetail(@PathVariable("id") Long id) {
        ItemDO itemDO = itemService.queryGoods(id);
        ItemVO itemVO = null;
        if (itemDO != null) {
            itemVO = new ItemVO();
            BeanUtils.copyProperties(itemDO, itemVO);
            PromoDO promo = promoService.getPromoByItemId(id);
            itemVO.setPromo(promo);
        }
        return Result.success(itemVO);
    }

    @DeleteMapping("/delete")
    public Result<Object> deleteGoods(@RequestParam("id") Long id) {
        boolean res = itemService.delete(id);
        return res ? Result.success("删除成功", null) : Result.fail("删除失败", null);
    }

    @PutMapping("/update")
    public Result<Object> deleteGoods(ItemDO goods) throws BusinessException {
        if (goods.getId() == null) {
            throw new BusinessException(500, "商品id不能为空");
        }
        boolean res = itemService.updateGoods(goods);
        return res ? Result.success("删除成功", null) : Result.fail("删除失败", null);
    }

    @GetMapping("/list")
    @Access
    public Result<Page<ItemVO>> deleteGoods(ItemDO goods, PageRequest pageRequest) throws BusinessException {
        Page<ItemVO> page = itemService.goodsList(goods, pageRequest);
        return Result.success(page);
    }




}
