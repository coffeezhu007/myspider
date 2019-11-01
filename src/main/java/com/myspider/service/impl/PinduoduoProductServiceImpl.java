package com.myspider.service.impl;

import com.myspider.dto.response.PinduoduoProductDetailFeignResponse;
import com.myspider.openapi.pinduoduo.PinduoduoFeignClient;
import com.myspider.service.PinduoduoProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PinduoduoProductServiceImpl implements PinduoduoProductService {

    @Value("${myspider.99api.user.apikey}")
    private String apiKey;

    @Autowired
    private PinduoduoFeignClient pinduoduoFeignClient;


    @Override
    public PinduoduoProductDetailFeignResponse findProductDetail(Long goodsId) {
        PinduoduoProductDetailFeignResponse  response = pinduoduoFeignClient.findProductDetail(apiKey,goodsId);
        return response;
    }

}
