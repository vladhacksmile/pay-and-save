package com.vtb.payandsave.request.target;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TargetWithdrawRequest {
    private Long card_id;
    private float amount;
}
