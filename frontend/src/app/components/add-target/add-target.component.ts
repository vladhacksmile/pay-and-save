import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {TokenStorageService} from "../../model/auth/TokenStorageService";
import {TargetService} from "../../service/target.service";
import {TargetRequest} from "../../request/TargetRequest";

@Component({
  selector: 'app-add-target',
  templateUrl: './add-target.component.html',
  styleUrls: ['./add-target.component.scss']
})
export class AddTargetComponent implements OnInit {

  form!: FormGroup;
  value1: any;
  info: any;
  priorities = [
    {name: "Высокий", value: 2, tooltip: "Here, is some message "},
    {name: "Средний", value: 1},
    {name: "Низкий", value : 0}
  ];

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
  selectedIcon!: any;


  constructor(
    private formBuilder: FormBuilder, private token: TokenStorageService, private targetService: TargetService) {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername()
    };
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      name: null,
      amount: null,
      priority: null,
      isSuperPriority: false,
      icon: null
    });
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
