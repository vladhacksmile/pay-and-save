package com.vtb.payandsave.entity.card;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vtb.payandsave.entity.Account;
import com.vtb.payandsave.model.card.CardUtils;
import com.vtb.payandsave.model.card.enums.CardPaymentSystem;
import com.vtb.payandsave.model.card.enums.CardRoundingStep;
import com.vtb.payandsave.model.card.enums.CardType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Locale;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "account_cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long card_id;
    @Enumerated(EnumType.ORDINAL)
    private CardType cardType;
    @Enumerated(EnumType.ORDINAL)
    private CardPaymentSystem cardPaymentSystem;
    @Enumerated(EnumType.ORDINAL)
    private CardRoundingStep cardRoundingStep = CardRoundingStep.STEP10;
    private Float amount;
    private boolean isActive;
    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;
    @OneToMany(targetEntity = CardTransaction.class, mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CardTransaction> cardTransactions;
    @NotNull
    private String cardExpiry;
    @NotNull
    private String encryptedPan;
    @NotNull
    private String cvv;
    @NotNull
    private String cardNumber;
    @NotNull
    private String embossingName;

    public Card(CardType cardType, CardPaymentSystem cardPaymentSystem, Account account) {
        this.cardType = cardType;
        this.cardPaymentSystem = cardPaymentSystem;
        this.amount = 0F;
        this.isActive = true;
        this.account = account;
        this.cardExpiry = "infinity";
        this.encryptedPan = String.valueOf(hashCode());
        this.cvv = String.valueOf(CardUtils.generateEncryptedPan(100, 999));
        this.cardNumber = "1000 " + CardUtils.generateEncryptedPan(1000, 9999) + " " + CardUtils.generateEncryptedPan(1000, 9999) + " " + CardUtils.generateEncryptedPan(1000, 9999);
        this.embossingName = account.getSurname().toUpperCase(Locale.ROOT) + " " + account.getName().toUpperCase(Locale.ROOT);
    }
}
