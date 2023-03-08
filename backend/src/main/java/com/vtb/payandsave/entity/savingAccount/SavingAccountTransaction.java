package com.vtb.payandsave.entity.savingAccount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @NotNull
    private LocalDateTime date;
    @NotNull
    private String name;
    @NotNull
    private String category;
    @NotNull
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
        this.date = LocalDateTime.now();
        this.operationSecurityCode = String.valueOf(hashCode());
    }
}
