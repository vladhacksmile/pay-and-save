import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../model/auth/TokenStorageService";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  authority: boolean = false;
  isCollapsed: boolean = true;

  constructor(private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.authority = true;
    }
  }

  logout() {
    this.tokenStorage.signOut();
    location.href = "/home";
  }

}
