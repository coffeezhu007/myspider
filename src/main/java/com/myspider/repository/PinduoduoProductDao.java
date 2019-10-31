package com.myspider.repository;

import com.myspider.entity.PinduoduoProductsUrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PinduoduoProductDao extends JpaRepository<PinduoduoProductsUrlEntity,Long> {

    @Modifying
    @Query(value = "UPDATE T_PDD_GOODS_URL SET STATUS = ?1 WHERE PRODUCT_URL = ?2", nativeQuery = true)
    int updatePinduoduoProductUrlStatus(int status,String productUrl);


    List<PinduoduoProductsUrlEntity> findByStatusOrderByIdAsc(int status);
}
