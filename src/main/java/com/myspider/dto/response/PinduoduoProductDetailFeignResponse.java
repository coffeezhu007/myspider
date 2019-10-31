package com.myspider.dto.response;

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
public class PinduoduoProductDetailFeignResponse implements Serializable {


    private String dataType;   // 数据类型
    private Boolean hasNext;  // 是否有下一页
    private String appCode;   // 数据类型
    private Integer page;   // 页数
    private String  version;   // 页数
    private String retcode;   // 返回状态码

    @JsonProperty("data")
    private PinduoduoProductDetailFeignData data;

}
