import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {AddTargetComponent} from "./components/add-target/add-target.component";
import {SettingsComponent} from "./components/settings/settings.component";
import {TargetInfoComponent} from "./components/target-info/target-info.component";
import {HelpComponent} from "./components/help/help.component";
import {LoginComponent} from "./components/login/login.component";
import {AuthComponent} from "./components/auth/auth.component";
import {RegisterComponent} from "./components/register/register.component";
import {MainComponent} from "./components/main/main.component";
import {CardComponent} from "./components/card/card.component";


const routes: Routes = [
  {
    path: 'home', component: HomeComponent,
  },
  {
    path: 'add-target', component: AddTargetComponent,
  },
  {
    path: 'login', component: LoginComponent,
  },
  {
    path: 'auth', component: AuthComponent,
  },
  {
    path: 'settings', component: SettingsComponent,
  },
  {
    path: 'register', component: RegisterComponent,
  },
  {
    path: 'target-info', component: TargetInfoComponent,
  },
  {
    path: 'help', component: HelpComponent,
  },
  {
    path: 'main', component: MainComponent,
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  { path: 'target/:id', component: TargetInfoComponent},
  { path: 'card', component: CardComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
