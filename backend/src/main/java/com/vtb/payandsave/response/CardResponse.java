package com.vtb.payandsave.response;

import com.vtb.payandsave.model.card.enums.CardRoundingStep;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CardResponse {
    private boolean active;
    private CardRoundingStep roundingStep;
}