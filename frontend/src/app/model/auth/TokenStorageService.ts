import { Injectable } from '@angular/core';

const TOKEN_KEY = 'AuthTokenVTB';
const USERNAME_KEY = 'AuthUsernameVTB';

@Injectable({
  providedIn: 'root'
})

export class TokenStorageService {
  constructor() { }

  public signOut() {
    localStorage.clear();
  }

  public saveToken(token: string) {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    return <string>localStorage.getItem(TOKEN_KEY);
  }

  public saveUsername(username: string) {
    localStorage.removeItem(USERNAME_KEY);
    localStorage.setItem(USERNAME_KEY, username);
  }

  public getUsername(): string {
    return <string> localStorage.getItem(USERNAME_KEY);
  }
}
