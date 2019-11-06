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
@Table(name="t_spider_token")
public class SpiderTokenEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="token")
    private String token;

    @Column(name="cdate")
    private Date createDate ;

}
