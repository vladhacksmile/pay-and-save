package com.vtb.payandsave.request.target;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
public class TargetWithdrawRequest {
    private Long card_id;
    private float amount;
}
