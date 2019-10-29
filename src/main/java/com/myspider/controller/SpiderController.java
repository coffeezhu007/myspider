package com.myspider.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SpiderController {

    @RequestMapping(name="/gospider",method = RequestMethod.GET)
    public String goSpider(){
        return "forward:/spider.html";
    }


}
