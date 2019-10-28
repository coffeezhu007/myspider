package com.myspider.openapi.taobao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import java.util.HashMap;
import java.util.List;


@FeignClient(name = "taobaoFeignClient", url = "${endpoint.99api.url}", configuration =
        TaobaoFeignConfig.class)
@RequestMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
public interface TaobaoFeignClient {


    /**
    *@Author:朱振兴
    *@Description:调用淘宝第三方接口的FeignClient
    *@Date:15:16 15:16
    */

    @GetMapping("/taobao/search")
    HashMap<String, Object> findProductInfo(@RequestParam("apikey") String apiKey,@RequestParam("keyword") String keyword);

}
