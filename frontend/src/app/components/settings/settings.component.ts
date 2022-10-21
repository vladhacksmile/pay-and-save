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
import {Profile} from "../../model/profile";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css'],
  providers: [MessageService]
})
export class SettingsComponent implements OnInit {
  form!: FormGroup;

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
  info: any;
  isUsedCashbackModel!: boolean;
  isPercentageOnBalanceModel!: boolean;
  isEvenDistributionModel!: boolean;
  constructor(
    private formBuilder: FormBuilder,
    private token: TokenStorageService,
    private userService: UserService,
    private msg: MessageService
  ) {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername()
    };

    this.form = this.formBuilder.group({
      name: null,
      surname: null,
      isUseCashBack: null,
      isEvenDistribution: null,
      isPercentageOnBalance: null
    })
  }

  ngOnInit(): void {
      this.userService.getProfile().subscribe(
          data => {
            this.profileData = data;
            this.isUsedCashbackModel = this.profileData.useCashBack;
            this.isPercentageOnBalanceModel = this.profileData.percentageOnBalance;
            this.isEvenDistributionModel = this.profileData.evenDistribution;
          },
          error => {
              //
          }
      );

  }

  onSubmit(): void{
    this.userService.updateProfile(new ProfileRequest(this.form.value.name, this.form.value.surname, this.form.value.isUseCashBack, this.form.value.isEvenDistribution, this.form.value.isPercentageOnBalance)).subscribe(
      data => {
        this.ngOnInit();
        this.msg.add({severity:'success', summary: 'Настройки', detail: 'Настройки успешно обновлены!'});
      },
      error => {

      }
    );
  }

}
