import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainComponent} from "./components/main/main.component";
import {HomeComponent} from "./components/home/home.component";
import {AddTargetComponent} from "./components/add-target/add-target.component";
import {SettingsComponent} from "./components/settings/settings.component";
import {TargetInfoComponent} from "./components/target-info/target-info.component";


const routes: Routes = [
  {
    path: 'add-target', component: AddTargetComponent,
  },
  {
    path: 'home', component: HomeComponent,
  },
  {
    path: 'settings', component: SettingsComponent,
  },
  {
    path: 'target-info', component: TargetInfoComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
