export class CardRequest {
  paymentSystem: CardPaymentSystem;
  type: CardType;

  constructor(paymentSystem: CardPaymentSystem, type: CardType) {
    this.paymentSystem = paymentSystem;
    this.type = type;
  }
}

enum CardType {
  DEBIT,CREDIT
}
enum CardPaymentSystem {
  VISA, MASTERCARD, MIR,
}

enum CardRoundingStep {
  STEP10, STEP50,STEP100, STEP1000
}
