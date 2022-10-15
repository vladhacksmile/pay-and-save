import {CardTransaction} from "./card-transaction";

export interface Card{
  "card_id": number,
  "cardType": string,
  "cardPaymentSystem": string,
  "cardRoundingStep": string,
  "amount": number,
  "cardTransactions": CardTransaction [],
  "cardExpiry": string,
  "encryptedPan": string,
  "cvv": string,
  "cardNumber": string,
  "embossingName": string,
  "active": boolean
}
