package com.vtb.payandsave.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vtb.payandsave.model.card.CardUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "card_transactions")
public class CardTransaction {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long transaction_id;
    @ManyToOne
    @JoinColumn(name = "card_id")
    @JsonIgnore
    private Card card;
    private String category;
    private Float amount;
    private Date date;
    private float cashback = 0;
    private float roundingAmount = 0;

    public CardTransaction(Card card, String category, Float amount) {
        this.card = card;
        this.category = category;
        this.amount = amount;
        this.date = new Date();

        float roundingAmount = CardUtils.calculateRoundingByAmount(card.getCardRoundingStep().getRoundingStep(), amount);
        if(card.getAccount().getTargets().size() != 0 && card.getAmount() >= roundingAmount && !category.equals("Пополнение цели")) {
            this.roundingAmount = roundingAmount;
        }

        if(!category.equals("Пополнение цели")) {
            this.cashback = amount / 100;
        }
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transaction_id=" + transaction_id +
                ", card=" + card +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", cashback=" + cashback +
                ", roundingAmount=" + roundingAmount +
                '}';
    }
}
