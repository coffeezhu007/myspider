package com.myspider.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class TaobaoProductInfoFeignResponse implements Serializable {


    @JsonProperty("items")
    private TaobaoProductInfoFeignData data;

}
