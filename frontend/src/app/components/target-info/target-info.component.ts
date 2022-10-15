import { Component, OnInit } from '@angular/core';
import {Target} from "../../model/target";
import {ActivatedRoute} from "@angular/router";
import {switchMap} from "rxjs";
import {TokenStorageService} from "../../model/auth/TokenStorageService";
import {TargetService} from "../../service/target.service";
import {UserService} from "../../service/user.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {TargetRequest} from "../../request/TargetRequest";

@Component({
  selector: 'app-target-info',
  templateUrl: './target-info.component.html',
  styleUrls: ['./target-info.component.scss']
})
export class TargetInfoComponent implements OnInit {
  targetInfo = {
    "target_id": 3,
    "icon_id": "sports_esports",
    "name": "Target example",
    "sum": 6.5,
    "amount": 1000.0,
    "priority": "HIGH",
    "creationDate": "2022-10-12T18:05:39.091+00:00",
    "savingAccount": {
      "percent": 10.0,
      "savingAccountTransactions": [
        {
          "amount": 1.625,
          "date": "2022-10-12T18:08:26.814+00:00",
          "category": "Какая-то транзакция"
        },
        {
          "amount": 3.25,
          "date": "2022-10-12T18:19:53.554+00:00",
          "category": "Какая-то транзакция"
        },
        {
          "amount": 1.625,
          "date": "2022-10-12T18:07:39.141+00:00",
          "category": "Какая-то транзакция"
        },
        {
          "amount": 1.625,
          "date": "2022-10-12T18:08:26.814+00:00",
          "category": "Какая-то транзакция"
        },
        {
          "amount": 3.25,
          "date": "2022-10-12T18:19:53.554+00:00",
          "category": "Какая-то транзакция"
        },
        {
          "amount": 1.625,
          "date": "2022-10-12T18:07:39.141+00:00",
          "category": "Какая-то транзакция"
        }
      ]
    },
    "completed": false
  };
  sourceTargetInfo = this.targetInfo;
  percentage = (this.targetInfo.sum/this.targetInfo.amount*100).toFixed(0);


  id: number | undefined;
  info: any;
  sourceName: string = this.targetInfo.name;
  form!: FormGroup;

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

  iconsZ = [
    {name: "shopping_cart", value: "Покупки"},
    {name: "school", value: "Обучение"},
    {name: "precision_manufacturing", value : "Техника"},
    {name: "checkroom", value : "Одежда"},
    {name: "restaurant", value : "Питание"},
    {name: "flight", value : "Путешествие"},
    {name: "chair", value : "Дом"},
    {name: "drive_eta", value : "Машина"},
    {name: "sports_esports", value : "Игры"}
  ];

  priorities = [
    {name: "Высокий", value: 2, tooltip: "Here, is some message "},
    {name: "Средний", value: 1},
    {name: "Низкий", value : 0}
  ];

  selectedIcon!: any;
  selectedPriority!: string | null;
  currentPriority: number = 2;
  currentIcon: string = "shopping_cart";

  constructor(private formBuilder: FormBuilder, private route: ActivatedRoute, private token: TokenStorageService, private targetService: TargetService) { }

  ngOnInit(): void {
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

    this.route.paramMap.pipe(
      switchMap(params => params.getAll('id'))
    )
      .subscribe(data=> this.id = +data);

    this.targetService.getTargetById(this.id).subscribe(
      data => {
        this.targetInfo = data;
        this.sourceName = data.name;
        if (this.targetInfo.priority == "HIGH") {
          this.currentPriority = 2;
        } else if (this.targetInfo.priority == "MIDDLE") {
          this.currentPriority = 1;
        } else {
          this.currentPriority = 0;
        }

        this.selectedIcon = this.targetInfo.icon_id;
        this.currentIcon = this.targetInfo.icon_id;
        this.percentage = (this.targetInfo.sum / this.targetInfo.amount * 100).toFixed(0);
      },
      error => {
        //
      }
    )

    this.selectedPriority = this.targetInfo.priority == 'HIGH'? "Высокий": this.targetInfo.priority == 'MEDIUM'? "Средний": this.targetInfo.priority == 'LOW'? "Низкий":null

  }

  onSubmit() {
    this.form.controls['icon'].setValue(this.selectedIcon.value);
    // alert(JSON.stringify(this.form.value));

    this.targetService.addTarget(new TargetRequest(this.form.value.icon, this.form.value.name, this.form.value.amount, this.form.value.priority, this.form.value.isSuperPriority)).subscribe(
      data => {
        location.href = "/main";
      },
      error => {

      }
    );
    // here must be code that check response, if all is good than return to main page
  }

}
