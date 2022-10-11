package com.vtb.payandsave.request;

import com.vtb.payandsave.model.card.CardPaymentSystem;
import com.vtb.payandsave.model.card.CardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CardRequest {
    private CardPaymentSystem paymentSystem;
    private CardType type;
}
