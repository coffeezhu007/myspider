package com.myspider.controller;

import com.myspider.dto.response.PinduoduoProductDetailFeignResponse;
import com.myspider.dto.response.TaobaoProductDetailFeignResponse;
import com.myspider.dto.response.TaobaoProductInfoFeignData;
import com.myspider.dto.response.TaobaoProductInfoFeignResponse;
import com.myspider.service.PinduoduoProductService;
import com.myspider.service.TaobaoProductService;
import com.myspider.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @Value("${taobao.search.product.min.page.number:1}")
    private Integer minPageNumber;
    @Value("${taobao.search.product.max.page.number:20}")
    private Integer maxPageNumber;


    @GetMapping("test1")
    public TaobaoProductInfoFeignResponse test(){
        TaobaoProductInfoFeignResponse response = taobaoProductService.getTaobaoProduct("手表","1","1000",1,"sale-desc");
        return response;
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
                    String goodsName = "";
                    String goodsDes = "";
                    String goodsPrice = "";
                    PinduoduoProductDetailFeignResponse response = pinduoduoProductService.findProductDetail(48495222275l);
                    if("0000".equals(response.getRetcode())){
                        if(null !=   response.getData()){
                            if(null != response.getData().getItem()){
                                goodsName =  response.getData().getItem().getGoodsName();
                                log.info("拼多多的商品名称是======{}",goodsName);
                                goodsDes =  response.getData().getItem().getGoodsDesc();
                                log.info("拼多多的商品描述是======{}",goodsDes);
                                goodsPrice =  response.getData().getItem().getMaxNormalPrice();
                                log.info("拼多多的商品的标准价格是======{}",goodsPrice);
                            }
                        }
                    }
                    // 第三步，通过第三方平台的API（拼多多商品详细API）得到拼多多该产品的所有信息 start

                    //第四步，通过第三方平台的API(淘宝商品) start

                    List<TaobaoProductInfoFeignData> taobaoProductInfoDataList = new ArrayList<>();

                    for(int i=minPageNumber;i<=maxPageNumber;i++){

                        TaobaoProductInfoFeignResponse  taobaoProductInfoResponse =   taobaoProductService.getTaobaoProduct(goodsName,"1",goodsPrice,i,"sale-desc");

                        if(null != taobaoProductInfoResponse.getData()){

                            // 再调用第三方的API(淘宝商品详情)可以得到该商品是不是包邮，如果包邮的，扔到taobaoProductInfoDataList 集合里面 start
                            String taobaoGoodsId = taobaoProductInfoResponse.getData().getId();

                            TaobaoProductDetailFeignResponse taobaoProductDetailFeignResponse = taobaoProductService.findProductDetail(taobaoGoodsId);
                            if(null != taobaoProductDetailFeignResponse.getData() &&  null != taobaoProductDetailFeignResponse.getData().getItem()){

                                String delivery = taobaoProductDetailFeignResponse.getData().getItem().getDelivery();

                                if("免运费".equals(delivery)|| "0.00".equals(delivery) ){
                                    taobaoProductInfoDataList.add(taobaoProductInfoResponse.getData());
                                }
                            }
                            // 再调用第三方的API(淘宝商品详情)可以得到该商品是不是包邮，如果包邮的，扔到taobaoProductInfoDataList 集合里面 end
                        }


                    }


                    //第四步，通过第三方平台的API(淘宝商品) end



                });

            }
        }

        return resultMap;
    }


}
