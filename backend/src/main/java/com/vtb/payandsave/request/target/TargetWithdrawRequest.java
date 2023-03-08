package com.vtb.payandsave.request.target;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@NoArgsConstructor
@Getter
@Setter
@Data
public class TargetWithdrawRequest {
    @Positive(message = "ID карты должен быть положительным!")
    private Long card_id;
    @PositiveOrZero(message = "Сумма цели должна быть положительной!")
    private float amount;
}
