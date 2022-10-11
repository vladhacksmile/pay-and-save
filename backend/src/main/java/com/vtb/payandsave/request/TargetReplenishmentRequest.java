package com.vtb.payandsave.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TargetReplenishmentRequest {
    private Long target_id;
    private Long card_id;
    private float amount;
}
