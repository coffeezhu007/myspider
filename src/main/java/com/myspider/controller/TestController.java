package com.myspider.controller;

import com.myspider.dto.response.PinduoduoProductDetailFeignResponse;
import com.myspider.dto.response.TaobaoProductDetailFeignResponse;
import com.myspider.dto.response.TaobaoProductInfoFeignData;
import com.myspider.dto.response.TaobaoProductInfoFeignResponse;
import com.myspider.service.PinduoduoProductService;
import com.myspider.service.TaobaoProductService;
import com.myspider.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
@Slf4j
public class TestController {

    @Autowired
    private TaobaoProductService taobaoProductService;

    @Autowired
    private PinduoduoProductService pinduoduoProductService;


    @GetMapping("test1")
    public PinduoduoProductDetailFeignResponse test1(){
        PinduoduoProductDetailFeignResponse response = pinduoduoProductService.findProductDetail(48495222275l);
        return response;
    }


    @GetMapping("test2")
    public TaobaoProductInfoFeignResponse test2(){
        TaobaoProductInfoFeignResponse response = taobaoProductService.getTaobaoProduct("手表","1","1000",1,40,"sale");
        return response;
    }


    @GetMapping("test3")
    public TaobaoProductInfoFeignResponse test3(){
        TaobaoProductInfoFeignResponse response = taobaoProductService.getTaobaoProductInfoByImgUrl("http://t00img.yangkeduo.com/goods/images/images/2019-09-28/80aed74d09c28ff26fbc9d8e5be094ee.jpeg");
        return response;
    }



}
