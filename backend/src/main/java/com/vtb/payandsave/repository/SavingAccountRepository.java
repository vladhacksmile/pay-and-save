package com.vtb.payandsave.repository;

import com.vtb.payandsave.entity.Card;
import com.vtb.payandsave.entity.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingAccountRepository extends JpaRepository<SavingAccount, Long> {

}
