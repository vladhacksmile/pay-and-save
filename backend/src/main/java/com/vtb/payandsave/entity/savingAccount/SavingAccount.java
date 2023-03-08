package com.vtb.payandsave.entity.savingAccount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vtb.payandsave.entity.Target;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "target_saving_accounts")
public class SavingAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long saving_account_id;
    @PositiveOrZero
    private float percent;
    @PositiveOrZero
    private float profit;
    @NotNull
    private LocalDateTime date;
    private boolean opened;
    @OneToOne
    @JoinColumn(name = "target_id")
    @JsonIgnore
    private Target target;
    @OneToMany(targetEntity = SavingAccountTransaction.class, mappedBy = "savingAccount", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SavingAccountTransaction> savingAccountTransactions;

    public SavingAccount(float percent) {
        this.percent = percent;
        this.opened = true;
        this.date = LocalDateTime.now();
    }
}
