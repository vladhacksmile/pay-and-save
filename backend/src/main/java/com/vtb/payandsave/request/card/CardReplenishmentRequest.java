package com.vtb.payandsave.request.card;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CardReplenishmentRequest {
    private float amount;

    public CardReplenishmentRequest(float amount){
        this.amount = amount;
    }
}
