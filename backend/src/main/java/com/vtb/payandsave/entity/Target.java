package com.vtb.payandsave.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vtb.payandsave.model.target.TargetPriority;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Entity
public class Target {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long target_id;
    private String icon_id;
    private String name;
    private Long sum; // * (возможно оно будет считаться автоматически)
    private Long amount; // всего нужно денег для цели
    @Enumerated(EnumType.ORDINAL)
    private TargetPriority priority;
    private boolean isSuperPriority;
    private boolean isCompleted;
    private Date creationDate;
    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    public Target(String icon_id, String name, Long amount, TargetPriority priority,
                  boolean isSuperPriority, Account account) {
        this.icon_id = icon_id;
        this.name = name;
        this.sum = 0L;
        this.amount = amount;
        this.priority = priority;
        this.isSuperPriority = isSuperPriority;
        this.isCompleted = false;
        this.creationDate = new Date();
        this.account = account;
    }

}
