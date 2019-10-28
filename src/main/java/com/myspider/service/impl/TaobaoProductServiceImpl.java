package com.myspider.service.impl;

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
    public HashMap<String, Object> getTaobaoProduct(String keyword) {
        HashMap<String, Object> taobaoMap = taobaoFeignClient.findProductInfo(apiKey,"手表");
        return taobaoMap;
    }
}
