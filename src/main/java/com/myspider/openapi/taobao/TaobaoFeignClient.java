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
@FeignClient(name = "taobaoFeignClient", url = "${endpoint.wanbangapi.url}", configuration =
        TaobaoFeignConfig.class)
@RequestMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
public interface TaobaoFeignClient {


    @GetMapping("/taobao/api_call.php?api_name=item_search")
    TaobaoProductInfoFeignResponse findProductInfo(@RequestParam("key") String apiKey, @RequestParam("secret") String secret,
                                                   @RequestParam("q") String keyword, @RequestParam("startPrice") String startPrice,
                                                   @RequestParam("endPrice") String endPrice, @RequestParam("page") Integer page,
                                                   @RequestParam("page_size") Integer pageSize,@RequestParam("sort") String sort);


}
