package com.myspider.service.impl;

import com.myspider.common.enums.StatusEnum;
import com.myspider.dto.response.PinduoduoProductDetailFeignResponse;
import com.myspider.dto.response.TaobaoProductInfoFeignData;
import com.myspider.dto.response.TaobaoProductInfoFeignResponse;
import com.myspider.entity.TaobaoProductsUrlEntity;
import com.myspider.repository.TaobaoProductDao;
import com.myspider.service.PinduoduoProductService;
import com.myspider.service.SpiderService;
import com.myspider.service.TaobaoProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class SpiderServiceImpl implements SpiderService {

    @Autowired
    private TaobaoProductService taobaoProductService;

    @Autowired
    private PinduoduoProductService pinduoduoProductService;

    @Autowired
    private TaobaoProductDao taobaoProductDao;

    @Value("${taobao.search.product.min.page.number:1}")
    private Integer minPageNumber;
    @Value("${taobao.search.product.max.page.page_size:40}")
    private Integer pageSize;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean spiderTaobaoData() throws Exception{

        Boolean result = Boolean.FALSE;

        List<TaobaoProductsUrlEntity> pddUrsList = taobaoProductDao.findByStatusOrderByIdAsc(StatusEnum.UNSPIDER.getValue());

        if(null != pddUrsList && pddUrsList.size() >0){

            pddUrsList.forEach(pddUrl ->{
                log.info("拼多多的商品url====={}",pddUrl.getPddProductUrl());

                // 第二步，把其中最后一段参数拿出来
                String goodsId = "";
                String [] urlSplit = pddUrl.getPddProductUrl().split("\\?");
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
                String thumbUrl = "";

                PinduoduoProductDetailFeignResponse response =  null;
                try{
                    response = pinduoduoProductService.findProductDetail(Long.valueOf(goodsId));

                    if("0000".equals(response.getRetcode())){
                        if(null !=   response.getData()){
                            if(null != response.getData().getItem()){
                                goodsName =  response.getData().getItem().getGoodsName();
                                log.info("拼多多的商品名称是======{}",goodsName);
                                goodsDes =  response.getData().getItem().getGoodsDesc();
                                log.info("拼多多的商品描述是======{}",goodsDes);
                                goodsPrice =  response.getData().getItem().getMinGroupPrice();
                                log.info("拼多多的商品的最低组合价格是======{}",goodsPrice);

                                thumbUrl = response.getData().getItem().getThumbUrl();
                                if(! thumbUrl.startsWith("http://")  &&  ! thumbUrl.startsWith("https://") ){
                                    thumbUrl =  "http:"+thumbUrl;
                                }
                                log.info("拼多多的商品的图片地址是======{}",thumbUrl);
                            }
                        }
                    }

                }
                catch (Exception e){
                    log.error("拼多多通过商品ID搜索商品发生异常,原因是===[{}] ",e.getMessage());
                    return;
                }
                // 第三步，通过第三方平台的API（拼多多商品详细API）得到拼多多该产品的所有信息 end

                //第四步，通过第三方平台的API(淘宝商品) start

                List<TaobaoProductInfoFeignData.TaobaoProductInfoFeignDataItem> taobaoProductInfoDataList = new ArrayList<>();

                //  第一层限定，找到的淘宝商品是小于等于我拼多多的商品的价格的
                TaobaoProductInfoFeignResponse taobaoProductInfoResponse =  null;

                try{
                    taobaoProductInfoResponse = taobaoProductService.getTaobaoProduct(goodsName,"1",goodsPrice,minPageNumber,pageSize,"sale");

                    if(null ==taobaoProductInfoResponse.getData().getItemData() || taobaoProductInfoResponse.getData().getItemData().size() ==0 ){

                        // 如果按索商品搜不到商品再用淘立拍接口再次精确的搜一下商品 start
                        try{
                            taobaoProductInfoResponse = taobaoProductService.getTaobaoProductInfoByImgUrl(thumbUrl);

                            if(null != taobaoProductInfoResponse.getData().getError() &&  !"".equals(taobaoProductInfoResponse.getData().getError())  ){

                                log.error("用图片搜商品也没找到数据,这样，data=‘搜索成功，但无结果’ ");

                                TaobaoProductsUrlEntity taobaoProductsUrlEntity = TaobaoProductsUrlEntity.builder().
                                        pddProductUrl(pddUrl.getPddProductUrl()).taoBaoProductUrl(null).spiderDate(new Date())
                                        .pddProductName(goodsName).pddProductPrice(new BigDecimal(goodsPrice))
                                        .status(StatusEnum.NO_TAOBAO_PRODUCT.getValue()).thumbUrl(thumbUrl).build();
                                try{
                                    taobaoProductDao.updateTaobaoProduct(taobaoProductsUrlEntity);
                                }
                                catch (Exception e){
                                    log.error("往taobaoUrl表中插入数据失败,原因是:[{}]",e.getMessage());
                                    throw e;
                                }
                                return;
                            }

                        }
                        catch(Exception e){
                            log.error("淘宝用拼多多图片搜索商品发生异常,原因是===[{}] ",e.getMessage());
                        }
                        // 如果按索商品搜不到商品再用淘立拍接口再次精确的搜一下商品 end
                    }
                }
                catch (Exception e){
                    log.error("淘宝用商品名字搜索商品发生异常,原因是===[{}] ",e.getMessage());
                }

                List<TaobaoProductInfoFeignData.TaobaoProductInfoFeignDataItem> taobaoProductInfoFeignDataList = null;

                if(null != taobaoProductInfoResponse && null != taobaoProductInfoResponse.getData()
                        && null != taobaoProductInfoResponse.getData().getItemData()
                        && taobaoProductInfoResponse.getData().getItemData().size() >0 ){

                    taobaoProductInfoFeignDataList = taobaoProductInfoResponse.getData().getItemData();

                    if(null != taobaoProductInfoFeignDataList && taobaoProductInfoFeignDataList.size()>0){

                        for(TaobaoProductInfoFeignData.TaobaoProductInfoFeignDataItem taobaoProductInfoFeignData: taobaoProductInfoFeignDataList ){
                            // 得到该商品是不是包邮，如果包邮的以及有销量的,并且价格小于等于拼多多的，扔到taobaoProductInfoDataList 集合里面 start
                            BigDecimal delivery = taobaoProductInfoFeignData.getPostFee();

                            if(null ==delivery || BigDecimal.valueOf(0.00d).equals(delivery)  && taobaoProductInfoFeignData.getSales()>0
                                    &&  taobaoProductInfoFeignData.getPrice().compareTo( new BigDecimal(goodsPrice)) <=0 ){
                                taobaoProductInfoDataList.add(taobaoProductInfoFeignData);
                            }
                            // 得到该商品是不是包邮，如果包邮的以及有销量的,并且价格小于等于拼多多的扔到taobaoProductInfoDataList 集合里面 end
                        }
                    }
                }
                //第四步，通过第三方平台的API(淘宝商品) end

                //第五步 最后按这个要求对比，从有销量的商品中，再找一个价格最低的那个淘宝网址就是想要的 start
                if(taobaoProductInfoDataList.size()==0 ){
                    // 未采到数据更新拼多多表，并记录状态是未对比到淘宝数据
                    log.info("通过淘宝关键字接口和图片接口找到对应的淘宝商品，但是不满足老板的要求，都放弃了，拼多多的商品id=[{}],商品名称=[{}]",goodsId,goodsName);

                    TaobaoProductsUrlEntity taobaoProductsUrlEntity = TaobaoProductsUrlEntity.builder().
                            pddProductUrl(pddUrl.getPddProductUrl()).taoBaoProductUrl(null).spiderDate(new Date())
                            .pddProductName(goodsName).pddProductPrice(new BigDecimal(goodsPrice))
                            .status(StatusEnum.TAOBAO_PRODUCT_CHECK_FAILED.getValue()).thumbUrl(thumbUrl).build();
                    try{
                        taobaoProductDao.updateTaobaoProduct(taobaoProductsUrlEntity);
                    }
                    catch (Exception e){
                        log.error("往taobaoUrl表中插入数据失败,原因是:[{}]",e.getMessage());
                        throw e;
                    }
                }else{
                    taobaoProductInfoDataList.sort((TaobaoProductInfoFeignData.TaobaoProductInfoFeignDataItem data1,TaobaoProductInfoFeignData.TaobaoProductInfoFeignDataItem data2)->
                            data1.getPromotionPrice().compareTo(data2.getPrice())  );

                    TaobaoProductInfoFeignData.TaobaoProductInfoFeignDataItem lowestTaobaoProductDataItem = taobaoProductInfoDataList.get(0);
                    // 第六步 把最优质的淘宝url存到 t_taobao_goods_url表中
                    String taobaoUrl = "";
                    if(! lowestTaobaoProductDataItem.getDetailUrl().startsWith("http://")  && ! lowestTaobaoProductDataItem.getDetailUrl().startsWith("https://") ){
                        taobaoUrl =  "http:"+lowestTaobaoProductDataItem.getDetailUrl();
                    }
                    log.info("最后得到的最优质的淘宝地址为======"+taobaoUrl);

                    TaobaoProductsUrlEntity taobaoProductsUrlEntity = TaobaoProductsUrlEntity.builder().
                            pddProductUrl(pddUrl.getPddProductUrl()).taoBaoProductUrl(taobaoUrl).spiderDate(new Date())
                            .pddProductName(goodsName).pddProductPrice(new BigDecimal(goodsPrice))
                            .status(StatusEnum.SUCCESS.getValue()).thumbUrl(thumbUrl).build();
                    try{
                        taobaoProductDao.updateTaobaoProduct(taobaoProductsUrlEntity);
                    }
                    catch (Exception e){
                        log.error("往taobaoUrl表中插入数据失败,原因是:[{}]",e.getMessage());
                        throw e;
                    }
                }
                //第五步 最后按这个要求对比，从有销量的商品中，再找一个价格最低的那个淘宝网址就是想要的 end

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            result = Boolean.TRUE;
        }
        return result;
    }

}
