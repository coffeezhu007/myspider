package com.myspider.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="t_taobao_goods_url")
public class TaobaoProductsUrlEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="pdd_product_url")
    private String pddProductUrl;

    @Column(name="taobao_product_url")
    private String tapBaoProductUrl;

    @Column(name="spider_date")
    private Date spiderDate ;

}
