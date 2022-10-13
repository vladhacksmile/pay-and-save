import { Component, OnInit } from '@angular/core';
import {Target} from "../../model/target";

@Component({
  selector: 'app-target-info',
  templateUrl: './target-info.component.html',
  styleUrls: ['./target-info.component.scss']
})
export class TargetInfoComponent implements OnInit {
  targetInfo: Target ={
    "target_id": 3,
    "icon_id": "sports_esports",
    "name": "Games",
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
  percentage = (this.targetInfo.sum/this.targetInfo.amount*100).toFixed(0);


  constructor() { }

  ngOnInit(): void {
  }

}
