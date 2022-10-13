import { Component, OnInit, Input } from '@angular/core';
import {Target} from "../../model/target";
@Component({
  selector: 'app-target',
  templateUrl: './target.component.html',
  styleUrls: ['./target.component.css']
})
export class TargetComponent implements OnInit {

  @Input()
  targetInfo!: Target;
  percentage!: string;
  constructor() {

  }

  ngOnInit() {
    this.percentage = (this.targetInfo.sum/this.targetInfo.amount*100).toFixed(0);
  }

}
