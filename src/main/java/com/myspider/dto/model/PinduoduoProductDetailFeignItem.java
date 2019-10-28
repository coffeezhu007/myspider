package com.myspider.dto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class PinduoduoProductDetailFeignItem implements Serializable {

    private String goodsName; //商品名称

    private String goodsDesc; //商品描述

    private String maxNormalPrice; //标准价格

    private String marketPrice; //市场价格

    private String thumbUrl; //图片地址

}
