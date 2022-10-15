import { Component, OnInit } from '@angular/core';
import {Card} from "../../model/card";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent implements OnInit {

  cards = [
    {
      "card_id": 1,
      "cardType": "DEBIT",
      "cardPaymentSystem": "MASTERCARD",
      "cardRoundingStep": "STEP10",
      "amount": 99574.3,
      "cardTransactions": [
        {
          "name": "Пополнение карты",
          "category": "Банковская операция",
          "amount": 100000.0,
          "date": "2022-10-15T02:33:30.737+00:00",
          "cashback": 0.0,
          "roundingAmount": 0.0,
          "percentageOnBalance": 0.0,
          "operationSecurityCode": "1791078220"
        },
        {
          "name": "Теремок",
          "category": "Кафе и рестораны",
          "amount": 430.0,
          "date": "2022-10-15T02:36:22.549+00:00",
          "cashback": 4.3,
          "roundingAmount": 0.0,
          "percentageOnBalance": 0.0,
          "operationSecurityCode": "119491981"
        }
      ],
      "cardExpiry": "infinity",
      "encryptedPan": "300076836",
      "cvv": "473",
      "cardNumber": "1000 6309 9981 3356",
      "embossingName": "ИВАНОВ ИВАН",
      "active": true
    }
  ];


  displayAddCardModal!: boolean;
  displaySettingsModal!: boolean;
  displayReplenishmentModal!: boolean;
  displayTransactionModal!: boolean;

  formAddCard!: FormGroup;
  formSettings!: FormGroup;
  formReplenishment!: FormGroup;
  formTransaction!: FormGroup;

  paymentSystem = [
    {name: "VISA", value: 0},
    {name: "MASTERCARD", value: 1},
    {name: "MIR", value : 2}
  ];

  type = [
    {name: "DEBIT", value: 0},
    {name: "CREDIT", value: 1}
  ];

  activity = [
    {name: "Да", value: 0},
    {name: "Нет", value: 1}
  ];

  roundingStep = [
    {name: "10", value: 0},
    {name: "50", value: 1},
    {name: "100", value: 2},
    {name: "1000", value: 3}
  ];



  selectedType: any;
  selectedPaymentSystem: any;
  selectedActivity: any;
  selectedRound: any;
  selectedCard!: Card;

  constructor(
    private formBuilder: FormBuilder,
  ) {
  }
  ngOnInit(): void {
    //Помести тут код для http request

    this.formAddCard = this.formBuilder.group({
      "paymentSystem": null,
      "type": null
    });

    this.formSettings = this.formBuilder.group({
      "active": null,
      "roundingStep": null
    });

    this.formReplenishment! = this.formBuilder.group({
      "amount": null
    });

    this.formTransaction! = this.formBuilder.group({
      "name": null,
      "category": null,
      "amount": null
    });

    this.selectedCard = this.cards[0];
  }

  onSubmitAddCard(): void {
    this.displayAddCardModal = false;
    this.formAddCard.controls['paymentSystem'].setValue(this.selectedPaymentSystem.value);
    this.formAddCard.controls['type'].setValue(this.selectedType.value);
    alert(JSON.stringify(this.formAddCard.value));
  }
  onSubmitSettings(): void {
    this.displaySettingsModal = false;
    this.formSettings.controls['active'].setValue(this.selectedActivity.value);
    this.formSettings.controls['roundingStep'].setValue(this.selectedRound.value);
    alert(JSON.stringify(this.formSettings.value));
  }
  onSubmitReplenishment(): void {
    this.displayReplenishmentModal = false;
    alert(JSON.stringify(this.formReplenishment.value));
  }
  onSubmitTransaction(): void {
    this.displayTransactionModal = false;
    alert(JSON.stringify(this.formTransaction.value));
  }
}
