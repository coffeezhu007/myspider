package com.myspider.controller;

import com.myspider.service.PinduoduoProductService;
import com.myspider.service.TaobaoProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private TaobaoProductService taobaoProductService;

    @Autowired
    private PinduoduoProductService pinduoduoProductService;

    @GetMapping("test1")
    public HashMap test(){
        HashMap<String,Object > resultMap =new HashMap<>();
        resultMap = taobaoProductService.getTaobaoProduct("手表");
        return resultMap;
    }

    @GetMapping("test2")
    public HashMap test2(){
        HashMap<String,Object > resultMap =new HashMap<>();
        resultMap = pinduoduoProductService.findProductDetail(5914165983l);
        return resultMap;
    }
}
