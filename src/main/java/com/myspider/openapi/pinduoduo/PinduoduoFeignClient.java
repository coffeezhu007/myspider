package com.myspider.openapi.pinduoduo;

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
    *@Description:调用拼多多第三方接口的FeignClient
    *@Date:15:16 15:16
    */
    @GetMapping("/pdd/search")
    HashMap<String, Object> findProductInfo(@RequestParam("apikey") String apiKey, @RequestParam("keyword") String keyword);

}
