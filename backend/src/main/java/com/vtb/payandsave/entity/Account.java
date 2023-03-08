package com.vtb.payandsave.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vtb.payandsave.entity.card.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Set;

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
    @NotNull
    @NotBlank
    @NotBlank(message = "Имя пользователя не может быть пустым!")
    private String username;
    @NotNull
    @NotBlank
    @JsonIgnore
    private String password;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
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

    public Account(@NotNull String username, @NotNull String password, @NotNull String name, @NotNull String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
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
