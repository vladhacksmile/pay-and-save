package com.vtb.payandsave.request.card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PayByCardRequest {
    private String name;
    private String category;
    private Float amount;
}
