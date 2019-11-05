package com.myspider.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PddStoreInfoFeignData  implements Serializable {

    @JsonProperty("seller")
    private PddStoreInfoFeignDataSeller sellerData;


    @JsonProperty("item")
    private PddStoreInfoFeignDataItem itemData;



    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PddStoreInfoFeignDataSeller implements Serializable{
        private String shopId;
        private String salesTip;
        private String goodsNum;
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PddStoreInfoFeignDataItem implements Serializable{

        private String url;
        private String maxGroupPrice;
        private String minGroupPrice;
        private String thumbUrl;
        private String salesTip;
        private String goodsName;
        private String goodsDesc;
    }
}
