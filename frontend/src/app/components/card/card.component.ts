import { Component, OnInit } from '@angular/core';
import {Card} from "../../model/card";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  cards: Card[];
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

  constructor(
    private formBuilder: FormBuilder,
  ) {
    this.cards = [
      {
        "card_id": 1,
        "cardType": "DEBIT",
        "cardPaymentSystem": "MASTERCARD",
        "cardRoundingStep": "STEP1000",
        "amount": 0.0,
        "cardTransactions": [],
        "cardExpiry": "infinity",
        "encryptedPan": "1301234413",
        "cvv": "204",
        "cardNumber": "1000 6272 2772 2554",
        "embossingName": "ИВАНОВ ИВАН",
        "active": true
      },
      {
        "card_id": 2,
        "cardType": "DEBIT",
        "cardPaymentSystem": "MASTERCARD",
        "cardRoundingStep": "STEP1000",
        "amount": 0.0,
        "cardTransactions": [],
        "cardExpiry": "infinity",
        "encryptedPan": "1301234413",
        "cvv": "204",
        "cardNumber": "1000 6272 2772 2554",
        "embossingName": "ИВАНОВ ИВАН",
        "active": true
      }
    ];
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
