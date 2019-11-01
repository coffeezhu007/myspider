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
@Table(name="t_taobao_goods_url")
public class TaobaoProductsUrlEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="pdd_product_url")
    private String pddProductUrl;

    @Column(name="pdd_product_pic_url")
    private String thumbUrl ;  // 图片url

    @Column(name="taobao_product_url")
    private String taoBaoProductUrl;

    @Column(name="cdate")
    private Date createDate ;

    @Column(name="spider_date")
    private Date spiderDate ;


    @Column(name="status",length = 1)
    private Integer status ;  // 预留暂时没有使用
}
