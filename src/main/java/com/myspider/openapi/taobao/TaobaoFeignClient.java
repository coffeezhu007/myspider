package com.myspider.openapi.taobao;

import com.myspider.dto.response.TaobaoProductDetailFeignResponse;
import com.myspider.dto.response.TaobaoProductInfoFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 *@Author:朱振兴
 *@Description:调用淘宝第三方接口的FeignClient
 *@Date:15:16 15:16
 */
@FeignClient(name = "taobaoFeignClient", url = "${endpoint.99api.url}", configuration =
        TaobaoFeignConfig.class)
@RequestMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
public interface TaobaoFeignClient {


    @GetMapping("/app/taobao/search")
    //@GetMapping("/taobao/search")
    TaobaoProductInfoFeignResponse findProductInfo(@RequestParam("apikey") String apiKey, @RequestParam("keyword") String keyword,
                                                   @RequestParam("startPrice") String startPrice,@RequestParam("endPrice") String endPrice,
                                                   @RequestParam("page") Integer page,@RequestParam("order") String order);


    @GetMapping("/taobao/detail")
    TaobaoProductDetailFeignResponse findProductDetail(@RequestParam("apikey") String apiKey, @RequestParam("itemid") String itemId);

}
