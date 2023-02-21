package com.cat.miaosha.service;

import com.cat.miaosha.common.Page;
import com.cat.miaosha.common.request.PageRequest;
import com.cat.miaosha.common.vo.ItemVO;
import com.cat.miaosha.entity.ItemDO;

import java.util.List;
import java.util.UUID;

/**
 * @author Mr.xin
 */
public interface ItemService {
    /**
     * 创建商品
     * @param itemDO 商品实体对象
     */
    boolean createItem(ItemDO itemDO);


    /**
     * 查询商品
     * @param id 商品id
     * @return 商品实体
     */
    ItemDO queryGoods(Long id);


    /**
     *
     * @param id 商品id
     * @return 删除结果
     */
    boolean delete(Long id);

    /**
     *
     * @param itemDO
     * @return
     */
    boolean updateGoods(ItemDO itemDO);

    Page<ItemVO> goodsList(ItemDO goods, PageRequest pageRequest);

    //库存扣减
    boolean decreaseStock(Long itemId,Integer amount);

    //商品销量增加
    boolean increaseSales(Long itemId,Integer amount);

    //初始化库存流水日志
    String initStockLog(Long itemId, Integer amount);



}
