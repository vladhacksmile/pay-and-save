package com.vtb.payandsave.entity.savingAccount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
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
    @PositiveOrZero
    private Float amount;
    @NotNull
    private LocalDateTime date;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String category;
    @NotNull
    @NotBlank
    private String operationSecurityCode;
    @ManyToOne
    @JoinColumn(name = "saving_account_id")
    @JsonIgnore
    private SavingAccount savingAccount;

    public SavingAccountTransaction(SavingAccount savingAccount, @NotNull String name, @NotNull String category, Float amount) {
        this.savingAccount = savingAccount;
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.operationSecurityCode = String.valueOf(hashCode());
    }
}
