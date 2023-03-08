package com.vtb.payandsave.request.card;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;

@NoArgsConstructor
@Getter
@Setter
@Data
public class CardReplenishmentRequest {
    @Min(value = 1, message = "Сумма пополнения должна быть минимум 1!")
    private float amount;

    public CardReplenishmentRequest(float amount){
        this.amount = amount;
    }
}
