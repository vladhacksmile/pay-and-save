package com.vtb.payandsave.request.target;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;

@NoArgsConstructor
@Getter
@Setter
@Data
public class TargetWithdrawRequest {
    private Long card_id;
    @Min(value = 1, message = "Сумма цели должна быть минимум 1!")
    private float amount;
}
