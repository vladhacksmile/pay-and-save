import { Component, OnInit } from '@angular/core';
import {Target} from "../../model/target";
import {TokenStorageService} from "../../model/auth/TokenStorageService";
import {TargetService} from "../../service/target.service";
import {TargetResponse} from "../../response/TargetResponse";
import {UserService} from "../../service/user.service";
import {ProfileResponse} from "../../response/ProfileResponse";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  targets!: Target[];
  basicData: any;
  value: any;
  basicOptions: any;
  responsiveOptions: any;
  info: any;
  profileData!: ProfileResponse;

  constructor(private token: TokenStorageService, private targetService: TargetService, private userService: UserService) {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername()
    };
    if (!this.token.getToken()) {
      location.href = "/login";
    }
    this.userService.getProfile().subscribe(
      data => {
        this.profileData = data;
      },
      error => {
        //
      }
    )
    this.targetService.getTargets().subscribe(
      data => {
        this.targets = data;
        for (let request of this.targets) {
          // let pointX = 130 + (request.x * 80) / request.r;
          // let pointY = 130 - (request.y * 80) / request.r;
          // this.drawPoint(pointX, pointY, request.belong);
        }
      },
      error => {
        // if(!error.ok)
        //   this.showDanger("Невозможно обработать запрос! Сервер недоступен!")
        // else
        //   this.showDanger(`${error.status}: ${error.error.message}`);
      }
    );
//     this.targets = [{
//       "target_id": 3,
//       "icon_id": "sports_esports",
//       "name": "Games",
//       "sum": 6.5,
//       "amount": 1000.0,
//       "priority": "HIGH",
//       "creationDate": "2022-10-12T18:05:39.091+00:00",
//       "savingAccount": {
//       "percent": 10.0,
//         "savingAccountTransactions": [
//         {
//           "amount": 1.625,
//           "date": "2022-10-12T18:08:26.814+00:00",
//           "category": "Какая-то транзакция"
//         },
//         {
//           "amount": 3.25,
//           "date": "2022-10-12T18:19:53.554+00:00",
//           "category": "Какая-то транзакция"
//         },
//         {
//           "amount": 1.625,
//           "date": "2022-10-12T18:07:39.141+00:00",
//           "category": "Какая-то транзакция"
//         }
//       ]
//     },
//     "completed": false
//   },
// {
//   "target_id": 5,
//   "icon_id": "school",
//   "name": "Обучение",
//   "sum": 3.5,
//   "amount": 100000.0,
//   "priority": "LOW",
//   "creationDate": "2022-10-12T18:06:43.117+00:00",
//   "savingAccount": {
//     "percent": 10.0,
//     "savingAccountTransactions": [
//       {
//         "amount": 0.875,
//         "date": "2022-10-12T18:07:39.143+00:00",
//         "category": "Какая-то транзакция"
//       },
//       {
//         "amount": 0.875,
//         "date": "2022-10-12T18:08:26.815+00:00",
//         "category": "Какая-то транзакция"
//       },
//       {
//         "amount": 1.75,
//         "date": "2022-10-12T18:19:53.575+00:00",
//         "category": "Какая-то транзакция"
//       }
//     ]
//   },
//   "completed": false
// }
//     ]

    //data content of graphics
    this.basicData = [{
      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July',  'May', 'Jdune', 'Judly', 'Mady', 'Juness', 'Julssy'],
      datasets: [
        {
          label: 'Тестовая цель',
          data: [110, 120, 130],
          fill: true,
          backgroundColor:'#42A5F573',
          borderColor: '#42A5F5',
        }
      ]
    },
      {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July',  'May', 'Jdune', 'Judly', 'Mady', 'Juness', 'Julssy'],
        datasets: [
          {
            label: 'Вторая тестовая цель',
            data: [28, 48, 40, 19, 86, 27, 90],
            fill: true,
            backgroundColor:'#42A5F573',
            borderColor: '#42A5F5'
          }
        ]
      }];

    //values of progress bar
    this.value = 10;

    //options of graphics
    this.basicOptions = {
      plugins: {
        legend: {
          labels: {
            color: '#495057'
          }
        }
      },
      scales: {
        x: {
          ticks: {
            color: '#495057'
          },
          grid: {
            color: '#ebedef'
          }
        },
        y: {
          ticks: {
            color: '#495057'
          },
          grid: {
            color: '#ebedef'
          }
        }
      }
    };

  }

  ngOnInit(): void {}
}
