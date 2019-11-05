package com.myspider.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpiderPddStoreForm implements Serializable {

    private Long productIdStart;
    private Long productIdEnd;
    private Long productCountStart;
    private Long productCountEnd;
    private Long salesCountStart;
    private Long salesCountEnd;
}
