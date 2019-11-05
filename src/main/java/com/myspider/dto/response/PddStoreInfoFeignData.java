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


    private Long mallId; //商铺id
    private Long mallSales; //销售提示
    private Long goodsNum; //货品数量
}
