package com.myspider.repository;

import com.myspider.entity.PinduoduoProductsUrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PinduoduoProductDao extends JpaRepository<PinduoduoProductsUrlEntity,Long> {


}
