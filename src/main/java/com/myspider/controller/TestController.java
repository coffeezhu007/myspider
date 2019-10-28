package com.myspider.controller;

import com.myspider.dto.response.PinduoduoProductDetailFeignResponse;
import com.myspider.service.PinduoduoProductService;
import com.myspider.service.TaobaoProductService;
import com.myspider.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("test")
@Slf4j
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
    public PinduoduoProductDetailFeignResponse test2(){
        PinduoduoProductDetailFeignResponse response = pinduoduoProductService.findProductDetail(48495222275l);
        return response;
    }

    @GetMapping("test3")
    public HashMap test3(){
        HashMap<String,Object > resultMap =new HashMap<>();

        String taskPath = "C:\\work\\Git\\respositry\\myspider\\myspider\\src\\main\\resources\\task\\spider_product.txt";

        // 如果任务文件存在，刚把文件里每一个url的最后一段读出来
        if(FileUtils.isExists(taskPath)){

            List<String> taskUrlList = FileUtils.readText2List(taskPath);

            if(null != taskUrlList && taskUrlList.size() >0){

                taskUrlList.forEach(urlText ->{
                    log.info("拼多多的商品url====={}",urlText);

                    // 第二步，把其中最后一段参数拿出来
                    String goodsId = "";
                    String [] urlSplit = urlText.split("\\?");
                    if(null != urlSplit && urlSplit.length >1){
                        goodsId = urlSplit[1];
                        log.info("拼多多的商品Id====={}",goodsId);
                    }

                    // 第三步，通过第三方平台的API（拼多多商品详细API）得到拼多多该产品的所有信息 start

                    PinduoduoProductDetailFeignResponse response = pinduoduoProductService.findProductDetail(48495222275l);
                    if("0000".equals(response.getRetcode())){
                        if(null !=   response.getData()){
                            if(null != response.getData().getItem()){

                               String goodsName =  response.getData().getItem().getGoodsName();
                               String goodsDesc =  response.getData().getItem().getGoodsDesc();

                            }
                        }

                    }
                    // 第三步，通过第三方平台的API（拼多多商品详细API）得到拼多多该产品的所有信息 start
                });

            }
        }

        return resultMap;
    }


}
