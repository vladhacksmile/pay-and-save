package com.vtb.payandsave.repository;

import com.vtb.payandsave.entity.savingAccount.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingAccountRepository extends JpaRepository<SavingAccount, Long> {

}
