import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {AddTargetComponent} from "./components/add-target/add-target.component";
import {SettingsComponent} from "./components/settings/settings.component";
import {TargetInfoComponent} from "./components/target-info/target-info.component";
import {HelpComponent} from "./components/help/help.component";
import {CardComponent} from "./components/card/card.component";


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
  },
  {
    path: 'help', component: HelpComponent,
  },
  {
    path: 'card', component: CardComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
