package com.myspider.service.impl;

import com.myspider.dto.response.TaobaoProductDetailFeignResponse;
import com.myspider.dto.response.TaobaoProductInfoFeignResponse;
import com.myspider.openapi.taobao.TaobaoFeignClient;
import com.myspider.service.TaobaoProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.HashMap;

@Service
@Slf4j
public class TaobaoProductServiceImpl implements TaobaoProductService {

    @Value("${myspider.99api.user.apikey}")
    private String apiKey;

    @Autowired
    private TaobaoFeignClient taobaoFeignClient;

    @Override
    public TaobaoProductInfoFeignResponse getTaobaoProduct(String keyword, String startPrice, String endPrice, Integer page,String order) {
        TaobaoProductInfoFeignResponse response  = taobaoFeignClient.findProductInfo(apiKey,keyword,startPrice,endPrice,page,order);
        return response;
    }

    @Override
    public TaobaoProductDetailFeignResponse findProductDetail(String itemId) {
        TaobaoProductDetailFeignResponse response = taobaoFeignClient.findProductDetail(apiKey,itemId);
        return response;
    }

}
