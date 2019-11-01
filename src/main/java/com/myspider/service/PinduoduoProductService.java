package com.myspider.service;

import com.myspider.dto.response.PinduoduoProductDetailFeignResponse;

public interface PinduoduoProductService {

    PinduoduoProductDetailFeignResponse findProductDetail(Long goodsId);

}
