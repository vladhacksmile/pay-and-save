import { Injectable } from '@angular/core';
import {TokenStorageService} from "../model/auth/TokenStorageService";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Target} from "../model/target";
import {TargetRequest} from "../request/TargetRequest";
import {Observable} from "rxjs";
import {Card} from "../model/card";
import {CardRequest} from "../request/CardRequest";
import {PayByCardRequest} from "../request/PayByCardRequest";
import {CardReplenishRequest} from "../request/CardReplenishRequest";
import {CardSettingsRequest} from "../request/CardSettingsRequest";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class CardService {
  private cardsUrl = 'http://localhost:8080/api/cards';

  constructor(private token: TokenStorageService, private http: HttpClient) {}

  public getCards() {
    return this.http.get<Card[]>(this.cardsUrl);
  }

  public getCardById(id: number | undefined) {
    return this.http.get<Card>(this.cardsUrl + "/" + id);
  }

  payByCardById(credentials: PayByCardRequest, id: number | undefined): Observable<any> {
    return this.http.post<any>(this.cardsUrl + "/" + id + "/pay", credentials, httpOptions);
  }

  replenishCardById(credentials: CardReplenishRequest, id: number | undefined): Observable<any> {
    return this.http.post<any>(this.cardsUrl + "/" + id + "/replenish", credentials, httpOptions);
  }

  settingsCardById(credentials: CardSettingsRequest, id: number | undefined): Observable<any> {
    return this.http.post<any>(this.cardsUrl + "/" + id + "/settings", credentials, httpOptions);
  }

  public getSettingsCardById(id: number | undefined) {
    return this.http.get<CardSettingsRequest>(this.cardsUrl + "/" + id + "/settings");
  }

  addCard(credentials: CardRequest): Observable<any> {
    return this.http.post<any>(this.cardsUrl, credentials, httpOptions);
  }
}
