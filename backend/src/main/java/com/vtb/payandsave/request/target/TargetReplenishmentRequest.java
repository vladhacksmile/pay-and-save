package com.vtb.payandsave.request.target;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TargetReplenishmentRequest {
    private Long card_id;
    private float amount;
}
