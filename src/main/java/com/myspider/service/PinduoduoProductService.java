package com.myspider.service;

import com.myspider.dto.response.PinduoduoProductDetailFeignResponse;
import com.myspider.entity.PinduoduoProductsUrlEntity;

import java.util.HashMap;

public interface PinduoduoProductService {

    PinduoduoProductDetailFeignResponse findProductDetail(Long goodsId);

    void savePinduoduoProductsUrl(String pddProductUrl);

}
