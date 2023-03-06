package com.vtb.payandsave.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vtb.payandsave.entity.card.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "accounts",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        })
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long account_id;
    private String username;
    @JsonIgnore
    private String password;
    private String name;
    private String surname;
    private Long superPriorityTarget_id;
    private boolean isUseCashback;
    private boolean isEvenDistribution;
    private boolean isPercentageOnBalance;
    private boolean usedService;
    @OneToMany(targetEntity = Target.class, mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Target> targets;
    @OneToMany(targetEntity = Card.class, mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Card> cards;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.name = "Иван";
        this.surname = "Иванов";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
