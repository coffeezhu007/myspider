package com.myspider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class SpiderPddStoreController {


    @RequestMapping(value="/spider_pdd_store",method = RequestMethod.GET)
    public String goSpider(){
        return "spider_pdd_store";
    }


}
