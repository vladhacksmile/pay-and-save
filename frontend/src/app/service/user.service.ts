import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {TokenStorageService} from "../model/auth/TokenStorageService";
import {Target} from "../model/target";
import {ProfileResponse} from "../response/ProfileResponse";
import {TargetRequest} from "../request/TargetRequest";
import {ProfileRequest} from "../request/ProfileRequest";
import {Profile} from "../model/profile";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private profileUrl = 'http://localhost:8080/api/profile';

  constructor(private token: TokenStorageService, private http: HttpClient) {}

  public getProfile() {
    return this.http.get<Profile>(this.profileUrl);
  }

  public updateProfile(credentials: ProfileRequest) {
    return this.http.post<any>(this.profileUrl, credentials, httpOptions);
  }
}
