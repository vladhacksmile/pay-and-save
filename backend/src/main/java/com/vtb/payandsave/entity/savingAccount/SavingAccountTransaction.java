package com.vtb.payandsave.entity.savingAccount;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long saving_account_transaction_id;
    private Float amount;
    private Date date;
    private String name;
    private String category;
    private String operationSecurityCode;
    @ManyToOne
    @JoinColumn(name = "saving_account_id")
    @JsonIgnore
    private SavingAccount savingAccount;

    public SavingAccountTransaction(SavingAccount savingAccount, String name, String category, Float amount) {
        this.savingAccount = savingAccount;
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.date = new Date();
        this.operationSecurityCode = String.valueOf(hashCode());
    }
}
