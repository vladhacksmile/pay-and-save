import { Component, OnInit } from '@angular/core';
import {Target} from "../../model/target";
import {TokenStorageService} from "../../model/auth/TokenStorageService";
import {TargetService} from "../../service/target.service";
import {UserService} from "../../service/user.service";
import {Profile} from "../../model/profile";

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

  constructor(private token: TokenStorageService,
              private targetService: TargetService,
              private userService: UserService) {

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

  ngOnInit(): void {
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
      },
      error => {

      }
    );
  }
}
