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

    @Value("${server.pinduoduo.task.file}")
    private String taskPath;

    @Value("${server.taobao.result.file}")
    private String outPutPath;

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
                        if(null != goodsId && goodsId.split("=").length >1 ){
                            goodsId = goodsId.split("=")[1];
                        }
                        log.info("拼多多的商品Id====={}",goodsId);
                    }

                    // 第三步，通过第三方平台的API（拼多多商品详细API）得到拼多多该产品的所有信息 start
                    String goodsName = "";
                    String goodsDes = "";
                    String goodsPrice = "";
                    PinduoduoProductDetailFeignResponse response = pinduoduoProductService.findProductDetail(Long.valueOf(goodsId));
                    if("0000".equals(response.getRetcode())){
                        if(null !=   response.getData()){
                            if(null != response.getData().getItem()){
                                goodsName =  response.getData().getItem().getGoodsName();
                                log.info("拼多多的商品名称是======{}",goodsName);
                                goodsDes =  response.getData().getItem().getGoodsDesc();
                                log.info("拼多多的商品描述是======{}",goodsDes);
                                goodsPrice =  response.getData().getItem().getMinGroupPrice();
                                log.info("拼多多的商品的最低组合价格是======{}",goodsPrice);
                            }
                        }
                    }
                    // 第三步，通过第三方平台的API（拼多多商品详细API）得到拼多多该产品的所有信息 start

                    //第四步，通过第三方平台的API(淘宝商品) start

                    List<TaobaoProductInfoFeignData> taobaoProductInfoDataList = new ArrayList<>();

                    for(int i=minPageNumber;i<=maxPageNumber;i++){

                        //  第一层限定，找到的淘宝商品是小于等于我拼多多的商品的价格的
                        TaobaoProductInfoFeignResponse  taobaoProductInfoResponse =  null;

                        try{
                            taobaoProductInfoResponse = taobaoProductService.getTaobaoProduct(goodsName,"1",goodsPrice,i,"sale-desc");
                        }
                        catch (Exception e){
                            log.error("taobaoProductInfoResponse.getData()，有可能无数据，这样，data=‘搜索成功，但无结果’ ");
                            log.error("翻到了第"+i+"页，已经无数据，这样，taobaoProductInfoResponse.data=‘搜索成功，但无结果’ ");
                            //跳出循环
                            break;
                        }

                        List<TaobaoProductInfoFeignData> taobaoProductInfoFeignDataList = null;

                        if(null != taobaoProductInfoResponse && null != taobaoProductInfoResponse.getData()){

                            taobaoProductInfoFeignDataList = taobaoProductInfoResponse.getData();

                            if(null != taobaoProductInfoFeignDataList && taobaoProductInfoFeignDataList.size()>0){

                                taobaoProductInfoFeignDataList.forEach(taobaoProductInfoFeignData ->{

                                    // 再调用第三方的API(淘宝商品详情)可以得到该商品是不是包邮，如果包邮的以及有销量的，扔到taobaoProductInfoDataList 集合里面 start
                                    String taobaoGoodsId = taobaoProductInfoFeignData.getId();

                                    TaobaoProductDetailFeignResponse taobaoProductDetailFeignResponse = taobaoProductService.findProductDetail(taobaoGoodsId);
                                    if(null != taobaoProductDetailFeignResponse.getData() &&  null != taobaoProductDetailFeignResponse.getData().getItem()){

                                        String delivery = taobaoProductDetailFeignResponse.getData().getItem().getDelivery();

                                        if("免运费".equals(delivery) || "0.00".equals(delivery) && !"0人付款".equals(taobaoProductInfoFeignData.getSales()) ){
                                            taobaoProductInfoDataList.add(taobaoProductInfoFeignData);
                                        }
                                    }
                                    // 再调用第三方的API(淘宝商品详情)可以得到该商品是不是包邮，如果包邮的以及有销量的，扔到taobaoProductInfoDataList 集合里面 end

                                });

                            }
                            /* if(!taobaoProductInfoResponse.getHasNext()){
                                break;
                            }*/
                        }
                    }
                    //第四步，通过第三方平台的API(淘宝商品) end

                    //第五步 最后按这个要求对比，从有销量的商品中，再找一个价格最低的那个淘宝网址就是想要的 start
                    if(taobaoProductInfoDataList.size()==0 ){
                        resultMap.put("result","没有对比到淘宝数据");
                    }

                    taobaoProductInfoDataList.sort((TaobaoProductInfoFeignData data1,TaobaoProductInfoFeignData data2)->
                            Double.valueOf(data1.getPrice()).compareTo(Double.valueOf(data2.getPrice())   )  );

                    TaobaoProductInfoFeignData lowestTaobaoProductData = taobaoProductInfoDataList.get(0);
                    //第五步 最后按这个要求对比，从有销量的商品中，再找一个价格最低的那个淘宝网址就是想要的 end

                    // 第六步 把最优质的淘宝url存到输出文件中
                    if(FileUtils.isExists(outPutPath)){

                        if(lowestTaobaoProductData.getUrl().startsWith("http://")  || lowestTaobaoProductData.getUrl().startsWith("https://") ){
                            log.info("最后得到的最优质的淘宝地址为======"+lowestTaobaoProductData.getUrl());
                            FileUtils.writeText(outPutPath,lowestTaobaoProductData.getUrl(),true);
                            resultMap.put("result",lowestTaobaoProductData.getUrl());
                        }
                        else{
                            log.info("最后得到的最优质的淘宝地址为======"+"https:"+lowestTaobaoProductData.getUrl());
                            FileUtils.writeText(outPutPath,"https:"+lowestTaobaoProductData.getUrl(),true);
                            resultMap.put("result","https:"+lowestTaobaoProductData.getUrl());
                        }
                    }

                });
            }
        }

        return resultMap;
    }

}
