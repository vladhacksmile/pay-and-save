package com.vtb.payandsave.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "saving_account_transactions")
public class SavingAccountTransaction {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long saving_account_transaction_id;
    private Float amount;
    private Date date;
    private String category;
    @ManyToOne
    @JoinColumn(name = "saving_account_id")
    @JsonIgnore
    private SavingAccount savingAccount;

    public SavingAccountTransaction(SavingAccount savingAccount, String category, Float amount) {
        this.savingAccount = savingAccount;
        this.category = category;
        this.amount = amount;
        this.date = new Date();
    }
}
