package com.myspider.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="t_pdd_goods_url")
public class PinduoduoProductsUrlEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="product_url")
    private String productUrl;

    @Column(name="spider_date")
    private Date spiderDate ;

    @Column(name="status",length = 1)
    private Integer status ;  // 1:代表已采集，0:代表示未采集

}
