import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../model/auth/TokenStorageService";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  info: any;
  data: any;
  username: string = "";
  constructor(private token: TokenStorageService) {
    this.username = token.getUsername();
  }

  ngOnInit(): void {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername()
    };
  }

  logout() {
    this.token.signOut();
    window.location.reload();
  }
}
