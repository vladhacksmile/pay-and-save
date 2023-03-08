package com.vtb.payandsave.request.target;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@AllArgsConstructor
@Getter
@Setter
@Data
public class TargetReplenishmentRequest {
    @Positive(message = "ID карты должен быть положительным!")
    private Long card_id;
    @PositiveOrZero(message = "Сумма пополнения должна быть положительной!")
    private float amount;
}
