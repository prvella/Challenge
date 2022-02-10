package com.soda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.soda.entity.TransactionDetailsEntity;

@Repository
public interface TransactionDetailsRepository
    extends JpaRepository<TransactionDetailsEntity, Long> {

}
