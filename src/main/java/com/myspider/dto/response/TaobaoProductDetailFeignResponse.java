package com.myspider.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaobaoProductDetailFeignResponse implements Serializable {

    private String dataType;   // 数据类型
    private Boolean hasNext;  // 是否有下一页
    private String appCode;   // 数据类型
    private Integer page;   // 翻页值
    private String  version;   // 页数
    private String retcode;   // 返回状态码
    private TaobaoProductDetailFeignData data;

}
