package com.soda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.soda.entity.SodaDetailsEntity;

@Repository
public interface SodaDetailsRepository extends JpaRepository<SodaDetailsEntity, Integer> {

}
