package com.myspider.dto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PinduoduoProductDetailFeignSeller implements Serializable {

    private String logisticsScore;  //售后评分

    private String serviceScore;  //服务评分

    private String shopName; //店铺名称

    private String logo; //标志

    private String shopId; //店铺id

    private String salesTip;// 销售提示

    private String shopUrl; //店铺链接

    private Long goodsNum; //商品数量

    private String descScore; // 描述评分

}
