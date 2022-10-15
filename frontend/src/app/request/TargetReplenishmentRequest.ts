export class TargetReplenishmentRequest {
  card_id: number;
  amount: number;

  constructor(card_id: number, amount: number) {
    this.card_id = card_id;
    this.amount = amount;
  }
}
