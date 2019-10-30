package com.myspider.repository;

import com.myspider.entity.TaobaoProductsUrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaobaoProductDao extends JpaRepository<TaobaoProductsUrlEntity,Long> {



}
