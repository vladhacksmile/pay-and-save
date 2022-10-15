import { Component, OnInit  } from '@angular/core';
import {TokenStorageService} from "./model/auth/TokenStorageService";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  authority: boolean = false;
  isCollapsed: boolean = true;

  constructor(private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.authority = true;
    }
  }

  logout() {
    this.tokenStorage.signOut();
    location.href = "/home";
  }
}
