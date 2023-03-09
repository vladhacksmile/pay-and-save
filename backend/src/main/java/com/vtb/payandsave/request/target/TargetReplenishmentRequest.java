package com.vtb.payandsave.request.target;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;

@AllArgsConstructor
@Getter
@Setter
@Data
public class TargetReplenishmentRequest {
    @Positive(message = "ID карты должен быть положительным!")
    private Long card_id;
    @Positive(message = "Сумма пополнения должна быть положительной!")
    private float amount;
}
