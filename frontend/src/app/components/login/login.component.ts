import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {AuthService} from "../../model/auth/auth.service";
import {TokenStorageService} from "../../model/auth/TokenStorageService";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private token: TokenStorageService) { }

  ngOnInit() {}

}
