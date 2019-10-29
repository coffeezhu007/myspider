package com.myspider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class SpiderController {


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
                System.out.println(url);
            }
        }
        catch (Exception e){
            log.error("[{}，上传文件失败，原因为：{}]",SpiderController.class.getName(),e.getMessage());
        }
        finally {

            try {
               bfr.close();
            } catch (IOException e) {
                log.error("[{}，关渚文件失败，原因为：{}]",SpiderController.class.getName(),e.getMessage());
            }
        }
        return resultMap;

    }

}
