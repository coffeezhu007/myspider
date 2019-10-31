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
    private Integer status ;  // 0:代表示未采集 1:代表已采集， 2:采集未得到结果


}
