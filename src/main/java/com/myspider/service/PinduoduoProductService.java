package com.myspider.service;

import com.myspider.dto.response.PinduoduoProductDetailFeignResponse;

import java.util.HashMap;

public interface PinduoduoProductService {

    PinduoduoProductDetailFeignResponse findProductDetail(Long goodsId);


}
