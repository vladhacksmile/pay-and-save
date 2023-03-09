package com.vtb.payandsave.request.card;

import lombok.*;

import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class CardReplenishmentRequest {
    @Positive(message = "Сумма пополнения должна быть положительной!")
    private float amount;
}
