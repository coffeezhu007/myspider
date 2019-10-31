package com.myspider.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaobaoProductInfoFeignData  implements Serializable {

    private String page;
    private String error;

    @JsonProperty("real_total_results")
    private String realTotalResults;

    @JsonProperty("total_results")
    private Integer totalResults;

    @JsonProperty("page_size")
    private Integer pageSize;

    @JsonProperty("item")
    private List<TaobaoProductInfoFeignDataItem> itemData;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TaobaoProductInfoFeignDataItem implements Serializable{

        @JsonProperty("num_iid")
        private Long id; //商品id

        private String title; // 标题

        @JsonProperty("pic_url")
        private String picUrl; //宝贝图片

        @JsonProperty("promotion_price")
        private BigDecimal promotionPrice; //优惠价

        private BigDecimal price ;//价格

        private Integer sales ;//销量

        @JsonProperty("sample_id")
        private String sampleId; //商品风格标识ID

        @JsonProperty("seller_nick")
        private String sellerNick; //商品风格标识ID

        @JsonProperty("post_fee")
        private BigDecimal postFee; //物流费用

        private String area; //店铺所在地

        @JsonProperty("detail_url")
        private String detailUrl; //宝贝链接

    }


}
