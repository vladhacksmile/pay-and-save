package com.vtb.payandsave.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vtb.payandsave.model.card.enums.CardPaymentSystem;
import com.vtb.payandsave.model.card.enums.CardRoundingStep;
import com.vtb.payandsave.model.card.enums.CardType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "account_cards")
public class Card {
    @Id
    @GeneratedValue
    private Long card_id;
    @Enumerated(EnumType.ORDINAL)
    private CardType cardType;
    @Enumerated(EnumType.ORDINAL)
    private CardPaymentSystem cardPaymentSystem;
    @Enumerated(EnumType.ORDINAL)
    private CardRoundingStep cardRoundingStep = CardRoundingStep.STEP100;
    private Float amount;
    private boolean isActive;
    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;
    @OneToMany(targetEntity = CardTransaction.class, mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CardTransaction> cardTransactions;

    public Card(CardType cardType, CardPaymentSystem cardPaymentSystem, Float amount, Account account) {
        this.cardType = cardType;
        this.cardPaymentSystem = cardPaymentSystem;
        this.amount = amount;
        this.isActive = true;
        this.account = account;
    }
}
