import { Component, OnInit } from '@angular/core';
import {AuthLoginInfo} from "../../model/auth/AuthLoginInfo";
import {Router} from "@angular/router";
import {AuthService} from "../../model/auth/auth.service";
import {TokenStorageService} from "../../model/auth/TokenStorageService";
import {Form, FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent implements OnInit {
  form!: FormGroup;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  username = "";
  private loginInfo: AuthLoginInfo = new AuthLoginInfo("", "");

  constructor(private router: Router, private authService: AuthService, private tokenStorage: TokenStorageService,
              private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: null,
      password: null
    })

    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      location.href = "/main";
    }
  }

  onSubmit() {
    if(this.form.value.username && this.form.value.password) {
      this.loginInfo = new AuthLoginInfo(
        this.form.value.username,
        this.form.value.password);

      this.authService.attemptAuth(this.loginInfo).subscribe(
        data => {
          this.tokenStorage.saveToken(data.token);
          this.tokenStorage.saveUsername(data.username);
          this.username = data.username;

          this.isLoginFailed = false;
          this.isLoggedIn = true;
          location.href = "/main";
        },
        error => {
          this.errorMessage = error.error.message;
          this.isLoginFailed = true;
        }
      );
    }
  }
}
