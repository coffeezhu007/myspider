package com.myspider.service.impl;

import com.myspider.dto.request.SpiderPddStoreForm;
import com.myspider.openapi.pinduoduo.PinduoduoFeignClient;
import com.myspider.repository.SpiderPddStoreDao;
import com.myspider.service.SpiderPddStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class SpiderPddStoreServiceImpl implements SpiderPddStoreService {

    @Value("${myspider.99api.user.apikey}")
    private String apiKey;

    @Autowired
    private PinduoduoFeignClient pinduoduoFeignClient;

    @Autowired
    private SpiderPddStoreDao spiderPddStoreDao;

    @Override
    public void savePddStoreInfo(SpiderPddStoreForm pddStoreForm) {

    }
}
