package com.vtb.payandsave.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Transaction {
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

    public Transaction(Card card, String category, Float amount) {
        this.card = card;
        this.category = category;
        this.amount = amount;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transaction_id=" + transaction_id +
                ", card=" + card +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
