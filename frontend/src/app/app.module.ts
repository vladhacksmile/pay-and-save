import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from "@angular/material/icon";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from "@angular/material/toolbar";
import { TargetComponent } from './components/target/target.component';

import { NavbarComponent } from './components/navbar/navbar.component';
import { ChartModule } from "primeng/chart";
import { ProgressBarModule } from 'primeng/progressbar';
import { ScrollPanelModule } from "primeng/scrollpanel";
import { CarouselModule } from "primeng/carousel";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { MainComponent } from './components/main/main.component';
import { AddTargetComponent } from './components/add-target/add-target.component';
import { SettingsComponent } from './components/settings/settings.component';
import {ButtonModule} from "primeng/button";
import {InputTextModule} from "primeng/inputtext";
import {InputNumberModule} from "primeng/inputnumber";
import {DropdownModule} from "primeng/dropdown";
import {InputSwitchModule} from "primeng/inputswitch";
import { TargetInfoComponent } from './components/target-info/target-info.component';
import {AvatarModule} from "primeng/avatar";
import {CardModule} from "primeng/card";
import { HelpComponent } from './components/help/help.component';
import {DialogModule} from "primeng/dialog";
import { CardComponent } from './components/card/card.component';
import {NgCreditCardModule} from "angular-credit-card";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    TargetComponent,
    NavbarComponent,
    MainComponent,
    AddTargetComponent,
    SettingsComponent,
    TargetInfoComponent,
    HelpComponent,
    CardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarModule,
    ChartModule,
    ProgressBarModule,
    ScrollPanelModule,
    CarouselModule,
    FormsModule,
    ReactiveFormsModule,
    ButtonModule,
    InputTextModule,
    InputNumberModule,
    DropdownModule,
    InputSwitchModule,
    AvatarModule,
    CardModule,
    DialogModule,
    NgCreditCardModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
