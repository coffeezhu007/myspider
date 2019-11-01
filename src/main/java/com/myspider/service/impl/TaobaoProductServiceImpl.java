package com.myspider.service.impl;

import com.myspider.common.enums.StatusEnum;
import com.myspider.dto.response.TaobaoProductDetailFeignResponse;
import com.myspider.dto.response.TaobaoProductInfoFeignResponse;
import com.myspider.entity.TaobaoProductsUrlEntity;
import com.myspider.openapi.taobao.TaobaoFeignClient;
import com.myspider.repository.TaobaoProductDao;
import com.myspider.service.TaobaoProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.HashMap;

@Service
@Slf4j
public class TaobaoProductServiceImpl implements TaobaoProductService {

    @Value("${myspider.wanbangapi.user.appkey}")
    private String apiKey;

    @Value("${myspider.wanbangapi.user.secret}")
    private String secret;

    @Autowired
    private TaobaoProductDao taobaoProductDao;

    @Autowired
    private TaobaoFeignClient taobaoFeignClient;

    @Override
    public void savePinduoduoUrlToDb(String pinduoduoUrl) {

        TaobaoProductsUrlEntity  taobaoProductsUrlEntity =
                TaobaoProductsUrlEntity.builder().pddProductUrl(pinduoduoUrl).status(StatusEnum.UNSPIDER.getValue())
                .createDate(new Date()).build();
        taobaoProductDao.save(taobaoProductsUrlEntity);

    }

    @Override
    public TaobaoProductInfoFeignResponse getTaobaoProduct(String keyword, String startPrice, String endPrice, Integer page,Integer pageSize,String sort) {
        TaobaoProductInfoFeignResponse response  = taobaoFeignClient.findProductInfo(apiKey,secret,keyword,startPrice,endPrice,page,pageSize,sort);
        return response;
    }

    @Override
    public TaobaoProductInfoFeignResponse getTaobaoProductInfoByImgUrl(String imgId) {
        TaobaoProductInfoFeignResponse response = taobaoFeignClient.findProductInfoByImgUrl(apiKey,secret,imgId);
        return response;
    }


}
