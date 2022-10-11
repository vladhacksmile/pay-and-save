package com.vtb.payandsave.repository;

import com.vtb.payandsave.entity.Target;
import com.vtb.payandsave.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
