package com.vtb.payandsave.request.card;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
public class CardReplenishmentRequest {
    private float amount;

    public CardReplenishmentRequest(float amount){
        this.amount = amount;
    }
}
