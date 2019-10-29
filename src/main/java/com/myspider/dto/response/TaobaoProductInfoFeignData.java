package com.myspider.dto.response;

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
public class TaobaoProductInfoFeignData  implements Serializable {

    private String id; //商品id
    private String title; // 标题
    private String url;// 商品链接
    private String seller; //卖家名称
    private String sellerId; //卖家id
    private String price ;//价格
    private String[] imageUrls ;//图片链接
    private String sales;  //销量,如：3309人付款
    private String commentCount; //评论数量
    private boolean isTmall; // 是否天猫
    private String marketPrice; //市场价格
    private String shopUrl; //店铺网址
}
