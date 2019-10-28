package com.myspider.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.myspider.dto.model.TaobaoProductDetailFeignItem;
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
public class TaobaoProductDetailFeignData  implements Serializable  {

    private TaobaoProductDetailFeignItem item;

}
