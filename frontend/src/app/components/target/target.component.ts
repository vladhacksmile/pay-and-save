import { Component, OnInit, Input } from '@angular/core';
import {Target} from "../../model/target";
import {TokenStorageService} from "../../model/auth/TokenStorageService";
import {TargetService} from "../../service/target.service";
import {UserService} from "../../service/user.service";
@Component({
  selector: 'app-target',
  templateUrl: './target.component.html',
  styleUrls: ['./target.component.css']
})
export class TargetComponent implements OnInit {

  @Input()
  targetInfo!: Target;
  percentage!: number;
  constructor(private token: TokenStorageService, private targetService: TargetService, private userService: UserService) {

  }

  ngOnInit() {
    this.percentage = Number((this.targetInfo.sum / this.targetInfo.amount * 100).toFixed(0));
    if(this.percentage>100){
      this.percentage = 100;
    }
  }

}
