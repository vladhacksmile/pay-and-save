package com.vtb.payandsave.entity;

import com.vtb.payandsave.model.card.CardType;
import com.vtb.payandsave.model.target.TargetPriority;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

public class Card {
    @Id
    private Long card_id;
    private Long account_id;
    @Enumerated(EnumType.ORDINAL)
    private CardType cardType;
    private Long amount;
    private boolean isActive;
}
