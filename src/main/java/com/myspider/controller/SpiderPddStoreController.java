package com.myspider.controller;

import com.myspider.dto.request.SpiderPddStoreForm;
import com.myspider.service.SpiderPddStoreService;
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
public class SpiderPddStoreController {


    @Autowired
    private SpiderPddStoreService spiderPddStoreService;

    @RequestMapping(value="/spider_pdd_store",method = RequestMethod.GET)
    public String goSpider(){
        return "spider_pdd_store";
    }


    @RequestMapping(value="/gospider_pdd_store",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> spiderPddStore(SpiderPddStoreForm pddStoreForm){

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status",false);

        try{
            spiderPddStoreService.savePddStoreInfo(pddStoreForm);
            resultMap.put("status",true);
        }
        catch(Exception e){
            throw e;
        }
        return resultMap;
    }
}
