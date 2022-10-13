export interface Card{
  "card_id": number,
  "cardType": string,
  "cardPaymentSystem": string,
  "cardRoundingStep": string,
  "amount": number,
  "cardTransactions": [
    {
      "category": string,
      "amount": number,
      "date": Date,
      "cashback": number,
      "roundingAmount": number
    }
  ],
  "cardExpiry": string,
  "encryptedPan": string,
  "cvv": string,
  "cardNumber": string,
  "embossingName": string,
  "active": boolean
}
