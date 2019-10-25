package com.myspider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping("test1")
    public HashMap test(){
        HashMap<String,Object > resultMap =new HashMap<>();
        resultMap.put("data",1);
        return resultMap;
    }
}
