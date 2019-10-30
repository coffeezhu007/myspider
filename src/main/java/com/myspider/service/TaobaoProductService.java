package com.myspider.service;

import com.myspider.dto.response.TaobaoProductDetailFeignResponse;
import com.myspider.dto.response.TaobaoProductInfoFeignResponse;


public interface TaobaoProductService {

     TaobaoProductInfoFeignResponse getTaobaoProduct(String keyword, String startPrice, String endPrice, Integer page,String order);

     TaobaoProductDetailFeignResponse findProductDetail(String itemId);
}
