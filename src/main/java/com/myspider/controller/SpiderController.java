package com.myspider.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SpiderController {

    @RequestMapping(value="/gospider",method = RequestMethod.GET)
    public String goSpider(){
        return "spider";
    }

}
