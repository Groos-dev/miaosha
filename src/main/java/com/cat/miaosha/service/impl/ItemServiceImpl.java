package com.cat.miaosha.service.impl;

import com.cat.miaosha.common.Page;

import com.cat.miaosha.common.request.PageRequest;
import com.cat.miaosha.common.vo.ItemVO;
import com.cat.miaosha.dao.ItemDOMapper;
import com.cat.miaosha.dao.ItemStockDOMapper;
import com.cat.miaosha.dao.StockLogDOMapper;
import com.cat.miaosha.entity.ItemDO;
import com.cat.miaosha.entity.ItemStockDO;
import com.cat.miaosha.entity.PromoDO;
import com.cat.miaosha.entity.StockLogDO;
import com.cat.miaosha.service.ItemService;
import com.cat.miaosha.service.PromoService;
import com.cat.miaosha.utils.BeanUtils;
import com.cat.miaosha.utils.IDUtils;
import com.cat.miaosha.utils.RedisUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Mr.xin
 */
@Service
public class ItemServiceImpl implements ItemService {

    private static String ITEM_PREFIX = "item_";
    // 防止缓存击穿
    private static ItemDO EMPTY_OBJECT = new ItemDO();
    @Resource
    private ItemDOMapper itemDOMapper;

    @Resource
    private ItemStockDOMapper itemStockDOMapper;

    @Resource
    private StockLogDOMapper stockLogDOMapper;

    @Resource
    private PromoService promoService;

    @Override
    public boolean createItem(ItemDO itemDO) {
        long id = IDUtils.generateId();
        itemDO.setId(id);
        int i = itemDOMapper.insert(itemDO);
        return i > 0;
    }

    @Override
    public ItemDO queryGoods(Long id) {
        ItemDO itemDO = null;
        itemDO = RedisUtils.get(ITEM_PREFIX + id);
        if (itemDO == null) {
            itemDO = itemDOMapper.selectByPrimaryKey(id);
        } else if (itemDO.getId() == null) {
            // redis 中缓存的空对象,数据库中一定不存在
            return null;
        }
        if (itemDO == null) {
            RedisUtils.set(ITEM_PREFIX + id, EMPTY_OBJECT, 1, TimeUnit.HOURS);
        } else {
            RedisUtils.set(ITEM_PREFIX + id, itemDO, 1, TimeUnit.HOURS);
        }
        return itemDO;
    }

    @Override
    public boolean delete(Long id) {
        return itemDOMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean updateGoods(ItemDO itemDO) {
        // todo 更新后需要更新缓存,是一个典型的数据不一致问题 解决办法：1.延时双删 2.串行化执行
        int updateRetCount = itemDOMapper.updateByPrimaryKeySelective(itemDO);
        return updateRetCount > 0;
    }

    @Override
    public Page<ItemVO> goodsList(ItemDO goods, PageRequest pageRequest) {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();
        com.github.pagehelper.Page<Object> pageHelper = PageHelper.startPage(pageNum, pageSize);
        List<ItemDO> list = itemDOMapper.selectListAll(goods, pageRequest);

        List<ItemVO> listVO = list.stream().map((itemDO) -> {
            ItemVO itemVO = new ItemVO();
            BeanUtils.copyProperties(itemDO, itemVO);
            ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
            PromoDO promo = promoService.getPromoByItemId(itemVO.getId());
            itemVO.setPromo(promo);
            itemVO.setStock(itemStockDO.getStock());
            return itemVO;
        }).collect(Collectors.toList());

        Page<ItemVO> page = new Page<ItemVO>();
        page.setData(listVO);
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setTotal(pageHelper.getTotal());
        return page;
    }

    @Override
    public boolean decreaseStock(Long itemId, Integer amount) {
        int updateRetCount = itemStockDOMapper.decreaseStock(itemId, amount);
        return updateRetCount > 0;
    }

    @Override
    public boolean increaseSales(Long itemId, Integer amount) {
        int updateRetCount = itemDOMapper.increaseSales(itemId, amount);
        return updateRetCount > 0;
    }

    @Override
    public String initStockLog(Long itemId, Integer amount) {
        StockLogDO stockLogDO = new StockLogDO();
        stockLogDO.setItemId(itemId);
        stockLogDO.setAmount(amount);
        stockLogDO.setStockLogId(UUID.randomUUID().toString().replace("-",""));
        stockLogDO.setStatus(1);

        stockLogDOMapper.insertSelective(stockLogDO);

        return stockLogDO.getStockLogId();

    }
}
