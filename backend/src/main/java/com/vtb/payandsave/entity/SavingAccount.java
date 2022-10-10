package com.vtb.payandsave.entity;

import javax.persistence.Id;

public class SavingAccount {
    @Id
    private Long savingAccount_id;
    private Long account_id; // One To Many
    private Long percent;
}
