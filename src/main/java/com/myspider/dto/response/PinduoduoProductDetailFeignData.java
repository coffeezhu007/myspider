package com.myspider.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myspider.dto.model.PinduoduoProductDetailFeignItem;
import com.myspider.dto.model.PinduoduoProductDetailFeignSeller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PinduoduoProductDetailFeignData implements Serializable {


    @JsonProperty("seller")
    private PinduoduoProductDetailFeignSeller seller;

    @JsonProperty("item")
    private PinduoduoProductDetailFeignItem item;

}
