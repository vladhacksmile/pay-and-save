package com.vtb.payandsave.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vtb.payandsave.entity.card.Card;
import com.vtb.payandsave.entity.savingAccount.SavingAccount;
import com.vtb.payandsave.model.target.TargetPriority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "account_targets")
public class Target {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long target_id;
    private String icon_id;
    private String name;
    private Float sum; // * (возможно оно будет считаться автоматически)
    private Float amount; // всего нужно денег для цели
    @Enumerated(EnumType.ORDINAL)
    private TargetPriority priority;
    private boolean isCompleted;
    private Date creationDate;
    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;
    @OneToOne(targetEntity = SavingAccount.class, mappedBy = "target", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SavingAccount savingAccount;

    public Target(String icon_id, String name, Float amount, TargetPriority priority,
                  Account account, SavingAccount savingAccount) {
        this.icon_id = icon_id;
        this.name = name;
        this.sum = 0F;
        this.amount = amount;
        this.priority = priority;
        this.isCompleted = false;
        this.creationDate = new Date();
        this.account = account;
        this.savingAccount = savingAccount;
    }

    public void setSum(Float sum) {
        this.sum = sum;
        if(sum >= amount) {
            this.isCompleted = true;
        }
    }

    @Override
    public String toString() {
        return "Target{" +
                "target_id=" + target_id +
                ", icon_id='" + icon_id + '\'' +
                ", name='" + name + '\'' +
                ", sum=" + sum +
                ", amount=" + amount +
                ", priority=" + priority +
                ", isCompleted=" + isCompleted +
                ", creationDate=" + creationDate +
                ", account=" + account +
                '}';
    }
}
