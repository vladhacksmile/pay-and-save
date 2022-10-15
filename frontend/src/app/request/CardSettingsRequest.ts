export class CardSettingsRequest {
  active: boolean;
  roundingStep: CardRoundingStep;

  constructor(active: boolean, roundingStep: CardRoundingStep) {
    this.active = active;
    this.roundingStep = roundingStep;
  }
}

enum CardRoundingStep {
  STEP10, STEP50,STEP100, STEP1000
}
