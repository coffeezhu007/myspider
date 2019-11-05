package com.myspider.openapi.pinduoduo;

import com.myspider.dto.response.PinduoduoProductDetailFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;


@FeignClient(name = "pinduoduoFeignClient", url = "${endpoint.99api.url}", configuration =
        PinduoduoFeignConfig.class)
@RequestMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
public interface PinduoduoFeignClient {


    /**
    *@Author:朱振兴
    *@Description:调用拼多多第三方接口的FeignClient---商品详情
    *@Date:15:16 15:16
    */
    @GetMapping("/pdd/detail")
    PinduoduoProductDetailFeignResponse findProductDetail(@RequestParam("apikey") String apiKey, @RequestParam("itemid") Long goodsId);


    @GetMapping("/pdd/info")
    PinduoduoProductDetailFeignResponse findProductDetail(@RequestParam("apikey") String apiKey, @RequestParam("itemid") Long goodsId);

}
