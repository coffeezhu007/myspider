package com.myspider.repository;

import com.myspider.entity.TaobaoProductsUrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaobaoProductDao extends JpaRepository<TaobaoProductsUrlEntity,Long> {

    List<TaobaoProductsUrlEntity> findByStatusOrderByIdAsc(Integer status);

    String UPDATE_TAOBAO_PRODUCT = "UPDATE T_TAOBAO_GOODS_URL SET STATUS = :#{#entity.status},SPIDER_DATE = :#{#entity.spiderDate} , " +
            "TAOBAO_PRODUCT_URL = :#{#entity.taoBaoProductUrl}, PDD_PRODUCT_PIC_URL = :#{#entity.thumbUrl},PDD_PRODUCT_NAME=:#{#entity.pddProductName}" +
            ",PDD_PRODUCT_PRICE = :#{#entity.pddProductPrice} WHERE PDD_PRODUCT_URL = :#{#entity.pddProductUrl}";
    @Modifying
    @Query(value=UPDATE_TAOBAO_PRODUCT,nativeQuery = true)
    void updateTaobaoProduct(@Param("entity") TaobaoProductsUrlEntity taobaoProductsUrlEntity);

}
