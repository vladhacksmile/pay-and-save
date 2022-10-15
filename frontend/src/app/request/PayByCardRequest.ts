export class PayByCardRequest {
  name: string;
  category: string;
  amount: number;

  constructor(name: string, category: string, amount: number) {
    this.name = name;
    this.category = category;
    this.amount = amount;
  }
}
