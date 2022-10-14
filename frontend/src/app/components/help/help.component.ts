import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-help',
  templateUrl: './help.component.html',
  styleUrls: ['./help.component.css']
})
export class HelpComponent implements OnInit {

  displayModal!: boolean;

  constructor() { }

  ngOnInit(): void {
  }

  showModalDialog() {
    this.displayModal = true;
  }

}
