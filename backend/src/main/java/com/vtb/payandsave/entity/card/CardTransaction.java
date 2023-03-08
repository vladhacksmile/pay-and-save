package com.vtb.payandsave.entity.card;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vtb.payandsave.model.card.CardUtils;
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
@Table(name = "card_transactions")
public class CardTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long transaction_id;
    @ManyToOne
    @JoinColumn(name = "card_id")
    @JsonIgnore
    private Card card;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String category;
    @PositiveOrZero
    private Float amount;
    @NotNull
    private LocalDateTime date;
    @PositiveOrZero
    private float cashback = 0;
    @PositiveOrZero
    private float roundingAmount = 0;
    @PositiveOrZero
    private float percentageOnBalance;
    @NotNull
    @NotBlank
    private String operationSecurityCode;

    public CardTransaction(Card card, @NotNull String name, @NotNull String category, Float amount) {
        this.card = card;
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.date = LocalDateTime.now();

        float roundingAmount = CardUtils.calculateRoundingByAmount(card.getCardRoundingStep().getRoundingStep(), amount);

        if(card.getAccount().getTargets().size() != 0 && card.getAmount() >= roundingAmount && !category.equals("Пополнение цели") && !category.equals("Банковская операция")) {
            this.roundingAmount = roundingAmount;
        }

        if(!category.equals("Пополнение цели") && !category.equals("Банковская операция")) {
            this.cashback = amount / 100;
        }

        this.operationSecurityCode = String.valueOf(hashCode());
    }

    @Override
    public String toString() {
        return "CardTransaction{" +
                "transaction_id=" + transaction_id +
                ", card=" + card +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", cashback=" + cashback +
                ", roundingAmount=" + roundingAmount +
                '}';
    }
}
