package com.vtb.payandsave.repository;

import com.vtb.payandsave.entity.CardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<CardTransaction, Long> {

}
