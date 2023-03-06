package com.vtb.payandsave.request.card;

import com.vtb.payandsave.model.card.enums.CardRoundingStep;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
public class CardSettingsRequest {
    private boolean active;
    private CardRoundingStep roundingStep;
}