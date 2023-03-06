package com.vtb.payandsave.request.target;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class TargetReplenishmentRequest {
    private Long card_id;
    private float amount;
}
