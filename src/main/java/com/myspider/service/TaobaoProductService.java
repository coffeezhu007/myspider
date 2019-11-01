package com.myspider.service;

import com.myspider.dto.response.TaobaoProductInfoFeignResponse;

public interface TaobaoProductService {

     void savePinduoduoUrlToDb(String pinduoduoUrl);

     TaobaoProductInfoFeignResponse getTaobaoProduct(String keyword, String startPrice, String endPrice, Integer page,Integer pageSize,String order);

     TaobaoProductInfoFeignResponse getTaobaoProductInfoByImgUrl(String imgId);
}
