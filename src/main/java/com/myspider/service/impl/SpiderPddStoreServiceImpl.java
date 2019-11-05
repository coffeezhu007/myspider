package com.myspider.service.impl;

import com.myspider.dto.request.SpiderPddStoreForm;
import com.myspider.dto.response.PddStoreInfoFeignData;
import com.myspider.dto.response.PddStoreInfoFeignResponse;
import com.myspider.entity.PddStoreInfoEntity;
import com.myspider.openapi.pinduoduo.PinduoduoFeignClient;
import com.myspider.repository.SpiderPddStoreDao;
import com.myspider.service.SpiderPddStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class SpiderPddStoreServiceImpl implements SpiderPddStoreService {

    @Value("${myspider.99api.user.apikey}")
    private String apiKey;

    @Autowired
    private PinduoduoFeignClient pinduoduoFeignClient;

    @Autowired
    private SpiderPddStoreDao spiderPddStoreDao;

    @Override
    public void savePddStoreInfo(SpiderPddStoreForm pddStoreForm) {

        PddStoreInfoFeignResponse storeInfoResponse = null;

        PddStoreInfoFeignData  pddStoreInfoData = null;

        // 从商品ID 开始的值到结束的值去拼拼里循环去碰.
        for(int i= pddStoreForm.getProductIdStart();i<=pddStoreForm.getProductIdEnd();i++){

            try{
                storeInfoResponse = pinduoduoFeignClient.findPinduoduoStoreInfo(apiKey,String.valueOf(i)) ;
                if(null != storeInfoResponse && "0000".equals(storeInfoResponse.getRetcode())){

                    if(null != storeInfoResponse.getPddStoreInfoFeignData() ){


                        pddStoreInfoData = storeInfoResponse.getPddStoreInfoFeignData();

                        if(pddStoreInfoData.getGoodsNum().intValue() >= pddStoreForm.getProductCountStart().intValue()
                                && pddStoreInfoData.getGoodsNum().intValue() <=  pddStoreForm.getProductCountStart().intValue()
                                && pddStoreInfoData.getMallSales().intValue() >= pddStoreForm.getSalesCountStart().intValue()
                                && pddStoreInfoData.getMallSales().intValue() <= pddStoreForm.getSalesCountEnd().intValue() ){

                            PddStoreInfoEntity storeInfoEntity = PddStoreInfoEntity.builder()
                                    .storeId(String.valueOf(pddStoreInfoData.getMallId()))
                                    .goodsNum(String.valueOf(pddStoreInfoData.getGoodsNum()))
                                    .mallSales(String.valueOf(pddStoreInfoData.getMallSales())).createDate(new Date()).build();

                            spiderPddStoreDao.saveAndFlush(storeInfoEntity);
                        }
                    }
                }
            }
            catch(Exception e){
                log.error("采集拼多多的店铺信息失败，发生异常，原因为:{}",e.getMessage());
                e.printStackTrace();
                continue;
            }
        }

    }
}
