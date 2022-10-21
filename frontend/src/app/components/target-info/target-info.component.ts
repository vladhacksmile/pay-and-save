import { Component, OnInit } from '@angular/core';
import {Target} from "../../model/target";
import {ActivatedRoute} from "@angular/router";
import {async, switchMap} from "rxjs";
import {TokenStorageService} from "../../model/auth/TokenStorageService";
import {TargetService} from "../../service/target.service";
import {UserService} from "../../service/user.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {TargetRequest} from "../../request/TargetRequest";
import {Card} from "../../model/card";
import {CardService} from "../../service/card.service";
import {MessageService} from "primeng/api";
import {TargetReplenishmentRequest} from "../../request/TargetReplenishmentRequest";
import {Profile} from "../../model/profile";
import {SavingAccount} from "../../model/saving-account";

export class TrObj implements Target {
  amount!: number;
  completed!: boolean;
  creationDate!: string;
  icon_id!: string;
  name!: string;
  priority!: string;
  savingAccount!: SavingAccount;
  sum!: number;
  target_id!: number;
}

@Component({
  selector: 'app-target-info',
  templateUrl: './target-info.component.html',
  styleUrls: ['./target-info.component.scss'],
  providers: [MessageService]
})
export class TargetInfoComponent implements OnInit {
  targetInfo: Target = {
    amount: 0,
    completed: false,
    creationDate: "",
    icon_id: "",
    name: "",
    priority: "",
    savingAccount: {
      percent: 0,
      savingAccountTransactions: [{
        amount: 0,
        date: "",
        category: ""
      }],
    },
    sum: 0,
    target_id: 0
  };


  percentage!: number;
  id: number | undefined;
  info: any;
  sourceName!: string;

  form!: FormGroup;
  formReplenish!: FormGroup;
  formWithdraw!: FormGroup;

  icons = [
    {name: "Покупки", value: "shopping_cart"},
    {name: "Обучение", value: "school"},
    {name: "Техника", value : "precision_manufacturing"},
    {name: "Одежда", value : "checkroom"},
    {name: "Питание", value : "restaurant"},
    {name: "Путешествие", value : "flight"},
    {name: "Дом", value : "chair"},
    {name: "Машина", value : "drive_eta"},
    {name: "Игры", value : "sports_esports"}
  ];


  priorities = [
    {name: "Высокий", value: 2},
    {name: "Средний", value: 1},
    {name: "Низкий", value : 0}
  ];


  hasCards: boolean = true;
  isSuper!: boolean;

  profileData: Profile = {
    account_id: 0,
    evenDistribution: false,
    name: "",
    percentageOnBalance: false,
    phoneNumber: "",
    superPriorityTarget_id: 0,
    surname: "",
    useCashBack: false

  };
  cards!: Card [];
  selectedCard!: Card;
  selectedIcon!: any;
  selectedPriority!: string | null;
  currentPriority!: number;
  currentIcon!: string;

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private token: TokenStorageService,
              private targetService: TargetService,
              private cardService: CardService,
              private userService: UserService,
              private msg: MessageService)
  {


    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername()
    };

    this.form = this.formBuilder.group({
      name: null,
      amount: null,
      priority: null,
      isSuperPriority: false,
      icon: null
    });

    this.formReplenish = this.formBuilder.group({
        amount: null
      }
    );

    this.formWithdraw = this.formBuilder.group({
        amount: null
      }
    )

    this.route.paramMap.pipe(
      switchMap(params => params.getAll('id'))
    ).subscribe(data=> this.id = +data);
  }



  ngOnInit(): void {
    this.getTargetInfo();
    this.getCards();
    this.getProfile();
  }

  getTargetInfo(): void{
    this.targetService.getTargetById(this.id).subscribe(
      data => {
        this.targetInfo = data;
        this.sourceName = data.name;

        if (this.targetInfo.priority === "HIGH"){
          this.currentPriority = 2;
        }else if (this.targetInfo.priority === "MEDIUM"){
          this.currentPriority = 1;
        }else {
          this.currentPriority = 0;
        }


        this.selectedIcon = this.targetInfo.icon_id;
        this.currentIcon = this.targetInfo.icon_id;


        this.percentage = Number((this.targetInfo.sum / this.targetInfo.amount * 100).toFixed(0));
        if(this.percentage>100){
          this.percentage = 100;
        }
      },
      error => {

      }
    );
  }

  getCards(): void {
    this.cardService.getCards().subscribe(
      data => {
        this.cards = data;
        if (this.cards.length === 0 ){
          this.msg.add({severity:'error', summary: 'Цель', detail: 'Нет существующих карт!'});
          this.hasCards = false;
        }else {
          this.selectedCard = this.cards[0];
        }
      },
      error => {
      }
    )
  }

  getProfile(): void {
    this.userService.getProfile().subscribe(
      data => {
        this.profileData = data;
        this.isSuper = this.profileData.superPriorityTarget_id == this.id;
      },
      error => {
        //
      }
    )
  }

  onSubmit() {
    if ((typeof this.selectedIcon).valueOf() === "object"){
      this.form.controls['icon'].setValue(this.selectedIcon.value);
    }

    this.targetService.updateTarget(new TargetRequest(this.form.value.icon, this.form.value.name, this.form.value.amount, this.form.value.priority, this.form.value.isSuperPriority), this.id).subscribe(
      data => {
        this.msg.add({severity:'success', summary: 'Цель', detail: 'Цель успешно обновлена!'});
        this.getTargetInfo();
      },
      error => {
      //
      }
    );
    // here must be code that check response, if all is good than return to main page
  }

  onSubmitReplenish() {


    this.targetService.replenishmentTarget(new TargetReplenishmentRequest(this.selectedCard.card_id, this.formReplenish.value.amount), this.id).subscribe(
      data => {
        this.msg.add({severity:'success', summary: 'Цель', detail: 'Цель успешно пополнена!'});
        this.getTargetInfo();
      },
      error => {
        this.msg.add({severity:'error', summary: 'Цель', detail: 'Нет средств на карте!'});
      }
    );

  }

  onSubmitWithdraw() {
    this.targetService.withdrawTarget(new TargetReplenishmentRequest(this.selectedCard.card_id, this.formWithdraw.value.amount), this.id).subscribe(
      data => {
        this.msg.add({severity:'success', summary: 'Цель', detail: 'Деньги с цели успешно выведены!'});
        this.getTargetInfo();
      },
      error => {
        this.msg.add({severity:'error', summary: 'Цель', detail: 'Нет средств на цели!'});
      }
    );
  }


}
