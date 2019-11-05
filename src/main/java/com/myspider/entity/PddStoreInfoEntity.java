package com.myspider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="t_pdd_store")
public class PddStoreInfoEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="store_id")
    private String storeId; //店铺id
    @Column(name="malll_sales")
    private String mallSales; //销售提示(里面包含销量)
    @Column(name="goodsNum")
    private String goodsNum; //商品数量
    @Column(name="cdate")
    private Date createDate ;

}
