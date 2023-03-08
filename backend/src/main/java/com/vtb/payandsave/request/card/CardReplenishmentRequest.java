package com.vtb.payandsave.request.card;

import lombok.*;

import javax.validation.constraints.PositiveOrZero;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class CardReplenishmentRequest {
    @PositiveOrZero(message = "Сумма пополнения должна быть положительной!")
    private float amount;
}
