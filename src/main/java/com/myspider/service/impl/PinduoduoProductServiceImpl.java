package com.myspider.service.impl;

import com.myspider.openapi.pinduoduo.PinduoduoFeignClient;
import com.myspider.service.PinduoduoProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
public class PinduoduoProductServiceImpl implements PinduoduoProductService {

    @Value("${myspider.99api.user.apikey}")
    private String apiKey;

    @Autowired
    private PinduoduoFeignClient pinduoduoFeignClient;

    @Override
    public HashMap<String, Object> getPinduoduoProduct(String keyword) {

        HashMap<String,Object> productMap = pinduoduoFeignClient.findProductInfo(apiKey,"手表");

        return productMap;
    }
}
