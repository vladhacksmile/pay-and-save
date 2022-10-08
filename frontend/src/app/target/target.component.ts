import { Component, OnInit } from '@angular/core';
import {MessageService} from "primeng/api";
import {ToastModule} from 'primeng/toast';
@Component({
  selector: 'app-target',
  templateUrl: './target.component.html',
  styleUrls: ['./target.component.css']
})
export class TargetComponent implements OnInit {

  value: number = 10;
  constructor() {}

  ngOnInit() {

  }

}
