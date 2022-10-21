import { Component, OnInit } from '@angular/core';
import {AuthLoginInfo} from "../../model/auth/AuthLoginInfo";
import {Router} from "@angular/router";
import {AuthService} from "../../model/auth/auth.service";
import {TokenStorageService} from "../../model/auth/TokenStorageService";
import {Form, FormBuilder, FormGroup} from "@angular/forms";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss'],
  providers: [MessageService]
})
export class AuthComponent implements OnInit {
  form!: FormGroup;
  username = "";
  private loginInfo: AuthLoginInfo = new AuthLoginInfo("", "");

  constructor(private router: Router, private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private formBuilder: FormBuilder,
              private msg: MessageService) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: null,
      password: null
    })

    if (this.tokenStorage.getToken()) {
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

          location.href = "/main";
        },
        error => {
          this.msg.add({severity:'error', summary: 'Вход', detail: 'Проверьте корректность введенных данных!'});
        }
      );
    }
  }
}
