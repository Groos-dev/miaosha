package com.cat.miaosha.service.impl;

import com.cat.miaosha.dao.PromoDOMapper;
import com.cat.miaosha.entity.PromoDO;
import com.cat.miaosha.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.xin
 */
@Service
public class PromoServiceImpl implements PromoService {


    @Autowired
    private PromoDOMapper promoDOMapper;

    @Override
    public PromoDO getPromoByItemId(Long itemId) {
        return promoDOMapper.selectByItemId(itemId);
    }


}
