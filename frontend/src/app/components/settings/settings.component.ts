import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ProfileResponse} from "../../response/ProfileResponse";
import {TokenStorageService} from "../../model/auth/TokenStorageService";
import {TargetService} from "../../service/target.service";
import {UserService} from "../../service/user.service";
import {TargetRequest} from "../../request/TargetRequest";
import {ProfileRequest} from "../../request/ProfileRequest";
import {ChangeDetectionStrategy,ChangeDetectorRef} from '@angular/core';
import {InputSwitchModule} from 'primeng/inputswitch';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SettingsComponent implements OnInit {
  form!: FormGroup;
  profileData!: ProfileResponse;
  info: any;
  isUsedCashback: boolean = false;
  isPercentageOnBalance: boolean = false;
  isEvenDistribution: boolean = false;
  constructor(
    private formBuilder: FormBuilder, private token: TokenStorageService, private userService: UserService) {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername()
    };
  }

  async ngOnInit(){
      this.userService.getProfile().subscribe(
          data => {
              this.profileData = data;
          },
          error => {
              //
          }
      )
      this.form = this.formBuilder.group({
          name: null,
          surname: null,
          isUseCashBack: false,
          isEvenDistribution: false,
          isPercentageOnBalance: false
      })
      await new Promise(f => setTimeout(f, 1000));
  }

    ngOnChanges() {
      if (this.profileData.isUseCashBack) {
          this.isUsedCashback = true;
      }
      if(this.profileData.isPercentageOnBalance) {
          this.isPercentageOnBalance = true;
      }
      if(this.profileData.isEvenDistribution) {
          this.isEvenDistribution = true;
      }
  }
  onSubmit(): void{
    console.log(this.form.value)
    this.userService.updateProfile(new ProfileRequest(this.form.value.name, this.form.value.surname, this.form.value.isUseCashBack, this.form.value.isEvenDistribution, this.form.value.isPercentageOnBalance)).subscribe(
      data => {
        this.userService.getProfile().subscribe(
          data => {
            this.profileData = data;
          },
          error => {
            //
          }
        )
      },
      error => {

      }
    );
  }

}
