package com.vtb.payandsave.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "accounts",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "phoneNumber")
        })
public class Account {
    @Id
    private Long account_id;
    private String phoneNumber;
    private String password;
    private String name;
    private String surname;
    private Long superPriorityTarget_id;
    private boolean isDebitMoney;
    private boolean isEvenDistribution;
    private boolean usedService;
    @OneToMany(targetEntity = Target.class, mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Target> targets;

    public Account(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
