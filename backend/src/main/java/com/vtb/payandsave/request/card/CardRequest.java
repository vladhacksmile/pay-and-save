package com.vtb.payandsave.request.card;

import com.vtb.payandsave.model.card.enums.CardPaymentSystem;
import com.vtb.payandsave.model.card.enums.CardType;
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
