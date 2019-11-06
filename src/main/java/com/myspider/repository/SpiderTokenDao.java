package com.myspider.repository;

import com.myspider.entity.SpiderTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpiderTokenDao extends JpaRepository<SpiderTokenEntity, Long> {



}
