import { Component, OnInit } from '@angular/core';
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  data: any;
  value: any;
  basicData: any;
  basicOptions: any;
  responsiveOptions: any;

  constructor() {

    //data content of graphics
    this.basicData = [{
      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July',  'May', 'Jdune', 'Judly', 'Mady', 'Juness', 'Julssy'],
      datasets: [
        {
          label: 'First Dataset',
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
            label: 'Second Dataset',
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
  }

}
