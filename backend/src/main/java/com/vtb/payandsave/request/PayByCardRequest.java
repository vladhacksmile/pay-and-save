package com.vtb.payandsave.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PayByCardRequest {
    private Long card_id;
    private String category;
    private Float amount;
}
