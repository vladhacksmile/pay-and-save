import { Component, OnInit } from '@angular/core';
import {SignUpInfo} from "../../model/auth/signup-info";
import {AuthService} from "../../model/auth/auth.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
  providers: [MessageService]
})
export class RegisterComponent implements OnInit {
  signupInfo: SignUpInfo | undefined;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';
  form!: FormGroup;

  constructor(private authService: AuthService, private formBuilder: FormBuilder, private msg: MessageService) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: null,
      password: null
    })
  }

  onSubmit() {
    this.signupInfo = new SignUpInfo(
      this.form.value.username,
      this.form.value.password);

    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        this.isSignedUp = true;
        this.isSignUpFailed = false;
        this.msg.add({severity:'success', summary: 'Регистрация', detail: 'Пользователь успешно зарегистрирован!'});
      },
      error => {
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
        this.msg.add({severity:'error', summary: 'Регистрация', detail: 'Существует пользователь с такими данными!'});
      }
    );
  }
}
