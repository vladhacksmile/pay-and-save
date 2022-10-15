export class ProfileResponse {
  name: string;
  surname: string;
  isUseCashBack: boolean;
  isEvenDistribution: boolean;
  isPercentageOnBalance: boolean;

  constructor(name: string, surname: string, isUseCashBack: boolean, isEvenDistribution:boolean, isPercentageOnBalance: boolean) {
    this.name = name;
    this.surname = surname;
    this.isUseCashBack = isUseCashBack;
    this.isEvenDistribution = isEvenDistribution;
    this.isPercentageOnBalance = isPercentageOnBalance;
  }
}
