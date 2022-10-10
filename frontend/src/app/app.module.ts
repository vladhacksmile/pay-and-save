import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from "@angular/material/icon";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from "@angular/material/toolbar";
import { TargetComponent } from './target/target.component';
import { TargetViewComponent } from './target-view/target-view.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ChartModule } from "primeng/chart";
import { ProgressBarModule } from 'primeng/progressbar';
import { ScrollPanelModule } from "primeng/scrollpanel";
import { CarouselModule } from "primeng/carousel";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { MainComponent } from './main/main.component';
import { AddTargetComponent } from './add-target/add-target.component';
import { SettingsComponent } from './settings/settings.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    TargetComponent,
    TargetViewComponent,
    NavbarComponent,
    MainComponent,
    AddTargetComponent,
    SettingsComponent
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
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
