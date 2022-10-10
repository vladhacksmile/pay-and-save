import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainComponent} from "./main/main.component";
import {LoginComponent} from "./login/login.component";
import {HomeComponent} from "./home/home.component";
import {AddTargetComponent} from "./add-target/add-target.component";
import {SettingsComponent} from "./settings/settings.component";

const routes: Routes = [
  {
    path: 'start', component: MainComponent, children: [
      {
        path: 'add-target', component: AddTargetComponent,
      },
      {
        path: 'home', component: HomeComponent,
      },
      {
        path: 'settings', component: SettingsComponent,
      },
    ]
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
