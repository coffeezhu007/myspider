package com.myspider.controller;

import com.myspider.service.SpiderTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class SpiderTokenController {

    @Autowired
    private SpiderTokenService spiderTokenService;

    @RequestMapping(value="/spider_generate_token",method = RequestMethod.GET)
    public String goGenerateToken(){
        return "spider_generate_token";
    }


    @RequestMapping(value="/generate_token",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> generateToken(){

        Map<String,Object> resultMap = new HashMap<>();

        try{
            resultMap.put("status",spiderTokenService.generateToken());
        }
        catch (Exception e){
            log.error("生成拼多多token失败");
            resultMap.put("status",false);
        }
        return resultMap;
    }

}
