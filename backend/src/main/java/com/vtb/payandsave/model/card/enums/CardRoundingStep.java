package com.vtb.payandsave.model.card.enums;

import lombok.Getter;

@Getter
public enum CardRoundingStep {
    STEP10(10), STEP50(50), STEP100(100), STEP1000(1000);

    private final int roundingStep;

    CardRoundingStep(int roundingStep) {
        this.roundingStep = roundingStep;
    }
}
