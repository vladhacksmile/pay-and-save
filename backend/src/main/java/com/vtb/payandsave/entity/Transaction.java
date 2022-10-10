package com.vtb.payandsave.entity;

import javax.persistence.Id;
import java.util.Date;

public class Transaction {
    @Id
    private Long transaction_id;
    private Long account_id; // OneToMany
    private Long card_id;
    private String category;
    private Date date;
    private Long amount;
}
