import { Component, OnInit } from '@angular/core';
import {Card} from "../../model/card";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {TokenStorageService} from "../../model/auth/TokenStorageService";
import {CardService} from "../../service/card.service";
import {CardRequest} from "../../request/CardRequest";
import {CardReplenishRequest} from "../../request/CardReplenishRequest";
import {PayByCardRequest} from "../../request/PayByCardRequest";
import {CardSettingsRequest} from "../../request/CardSettingsRequest";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss'],
  providers: [MessageService]
})
export class CardComponent implements OnInit {

  cards!: Card [];


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
    {name: "Да", value: 1},
    {name: "Нет", value: 0}
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
  selectedCard: Card = {
    active: false,
    amount: 0,
    cardExpiry: "",
    cardNumber: "",
    cardPaymentSystem: "",
    cardRoundingStep: "",
    cardTransactions: [],
    cardType: "",
    card_id: 0,
    cvv: "",
    embossingName: "",
    encryptedPan: ""
  };
  info: any;


  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private token: TokenStorageService,
    private cardService: CardService,
    private msg: MessageService
  ) {
    this.cards = [];

  }
  ngOnInit(): void {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername()
    };

    this.getCards();


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

  getCards(): void{
    this.cardService.getCards().subscribe(
      data => {
        this.cards = data;
        this.selectedCard = this.cards[0];
      },
      error => {
        //
      }
    )
  }

  onSubmitAddCard(): void {
    this.displayAddCardModal = false;
    this.formAddCard.controls['paymentSystem'].setValue(this.selectedPaymentSystem.value);
    this.formAddCard.controls['type'].setValue(this.selectedType.value);
    // alert(JSON.stringify(this.formAddCard.value));
    this.cardService.addCard(new CardRequest(this.formAddCard.value.paymentSystem, this.formAddCard.value.type)).subscribe(
      data => {
        this.msg.add({severity:'success', summary: 'Карты', detail: 'Карта успешно добавлена!'});
      },
      error => {
      }
    );
  }
  onSubmitSettings(): void {
    this.displaySettingsModal = false;
    this.formSettings.controls['active'].setValue(this.selectedActivity.value);
    this.formSettings.controls['roundingStep'].setValue(this.selectedRound.value);
    // alert(JSON.stringify(this.formSettings.value));
    this.cardService.settingsCardById(new CardSettingsRequest(this.formSettings.value.active, this.formSettings.value.roundingStep), this.selectedCard.card_id).subscribe(
      data => {
        this.msg.add({severity:'success', summary: 'Карты', detail: 'Настройки успешно изменены!'});
        this.ngOnInit();
      },
      error => {

      }
    );
  }

  onSubmitReplenishment(): void {
    this.displayReplenishmentModal = false;
    //alert(JSON.stringify(this.formReplenishment.value));
    this.cardService.replenishCardById(new CardReplenishRequest(this.formReplenishment.value.amount), this.selectedCard.card_id).subscribe(
      data => {
        this.msg.add({severity:'success', summary: 'Карты', detail: 'Деньги успешно пополнены!'});
        this.ngOnInit();
      },
      error => {

      }
    );

  }
  onSubmitTransaction(): void {
    this.displayTransactionModal = false;
    //alert(JSON.stringify(this.formTransaction.value));
    this.cardService.payByCardById(new PayByCardRequest(this.formTransaction.value.name, this.formTransaction.value.category, this.formTransaction.value.amount), this.selectedCard.card_id).subscribe(
      data => {
        this.msg.add({severity:'success', summary: 'Карты', detail: 'Транзакция успешно проведена!'});
        this.ngOnInit();
      },
      error => {
        this.msg.add({severity:'error', summary: 'Карты', detail: 'На балансе не достаточно средств!'});
      }
    );
  }
}
