package com.myspider.controller;

import com.myspider.service.PinduoduoProductService;
import com.myspider.service.SpiderService;
import com.myspider.service.TaobaoProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class SpiderController {

    @Autowired
    private TaobaoProductService taobaoProductService;

    @Autowired
    private SpiderService spiderService;

    @Value("${spring.servlet.multipart.location}")
    private String upladFileDir;

    @RequestMapping(value="/gospider",method = RequestMethod.GET)
    public String goSpider(){
        return "spider";
    }


    @RequestMapping(value="/upladPddfile",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> upladPddfile(@RequestParam("pdd_product_file") MultipartFile pddProductFile){

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status",false);
        String fileName = pddProductFile.getOriginalFilename();

        BufferedReader bfr = null;

        try{
            File upladDirFile = new File(upladFileDir+File.separator+"pdd_product/");
            if(!upladDirFile.exists()){
                upladDirFile.mkdirs();
            }
            // 第一步文件保存在这里

            File dest = new File(upladDirFile.getAbsoluteFile()+File.separator+ fileName);
            pddProductFile.transferTo(dest);

            // 第二步读取文件中的内容
            bfr =  new BufferedReader(new  FileReader(dest));
            String url = "";
            while (( url=bfr.readLine())!=null) {
                try{
                    taobaoProductService.savePinduoduoUrlToDb(url);
                }
                catch (Exception e){
                    log.error("[{}，插入拼多多商品数据失败，原因为：{}]",SpiderController.class.getName(),e.getMessage());
                }
            }
            resultMap.put("status",true);
        }
        catch (Exception e){
            log.error("[{}，上传文件失败，原因为：{}]",SpiderController.class.getName(),e.getMessage());
        }
        finally {

            try {
               bfr.close();
            } catch (IOException e) {
                log.error("[{}，关闭文件失败，原因为：{}]",SpiderController.class.getName(),e.getMessage());
            }
        }
        return resultMap;

    }


    @RequestMapping(value="/spiderData",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> spiderData(){

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status",false);
        try{
            boolean result = spiderService.spiderTaobaoData();
            resultMap.put("status",result);
        }
        catch (Exception e){
            resultMap.put("status",false);
            log.error("[{}，对比淘宝数据失败，原因为：{}]",SpiderController.class.getName(),e.getMessage());
        }

        return resultMap;
    }

}
