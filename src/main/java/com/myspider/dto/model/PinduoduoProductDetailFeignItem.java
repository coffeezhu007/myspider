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

    private String goodsName;

    private String goodsDesc;

    private String maxNormalPrice;

    private String marketPrice;

    private String thumbUrl;

}
