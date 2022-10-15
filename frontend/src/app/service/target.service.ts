import { Injectable } from '@angular/core';
import {TokenStorageService} from "../model/auth/TokenStorageService";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {TargetResponse} from "../response/TargetResponse";
import {TargetRequest} from "../request/TargetRequest";
import {Target} from "../model/target";
import {TargetReplenishmentRequest} from "../request/TargetReplenishmentRequest";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class TargetService {

  private targetsUrl = 'http://localhost:8080/api/targets';
  private addTargetUrl = 'http://localhost:8080/api/targets';

  constructor(private token: TokenStorageService, private http: HttpClient) {}

  public getTargets() {
    return this.http.get<Target[]>(this.targetsUrl);
  }

  public getTargetById(id: number | undefined) {
    return this.http.get<Target>(this.targetsUrl + "/" + id);
  }

  addTarget(credentials: TargetRequest): Observable<any> {
    return this.http.post<any>(this.addTargetUrl, credentials, httpOptions);
  }

  updateTarget(credentials: TargetRequest, id: number | undefined): Observable<any> {
    return this.http.put<any>(this.addTargetUrl + "/" + id, credentials, httpOptions);
  }

  replenishmentTarget(credentials: TargetReplenishmentRequest, id: number | undefined) {
    return this.http.post<any>(this.addTargetUrl + "/" + id + "/replenishment", credentials, httpOptions);
  }

  withdrawTarget(credentials: TargetReplenishmentRequest, id: number | undefined) {
    return this.http.post<any>(this.addTargetUrl + "/" + id + "/withdraw", credentials, httpOptions);
  }

}
