package com.vtb.payandsave.request.target;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@AllArgsConstructor
@Getter
@Setter
@Data
public class TargetReplenishmentRequest {
    private Long card_id;
    @Min(value = 1, message = "Сумма пополнения должна быть минимум 1!")
    private float amount;
}
