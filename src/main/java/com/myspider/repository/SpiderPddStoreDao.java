package com.myspider.repository;

import com.myspider.entity.PddStoreInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpiderPddStoreDao extends JpaRepository<PddStoreInfoEntity, Long> {


}
