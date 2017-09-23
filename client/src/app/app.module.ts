import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import {MdCardModule} from '@angular/material';

import { AlertModule } from 'ngx-bootstrap';
import { Ng2DragDropModule } from 'ng2-drag-drop';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { FooterComponent } from './footer/footer.component';
import { TaskCardComponent } from './task-card/task-card.component';
import { ManagerComponent } from './tasks/manager.component';
import { TeamMemberComponent} from './tasks/team-member.component';
import {TeamMemberCardComponent} from './tasks/team-member-card.component';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {BusyModule} from 'angular2-busy';

const appRoutes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'tasks',
    component: ManagerComponent
  },
  {
    path: 'team-member',
    component: TeamMemberComponent
  },
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  }
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    HomeComponent,
    FooterComponent,
    TaskCardComponent,
    ManagerComponent,
    TeamMemberComponent,
    TeamMemberCardComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    BusyModule,
    NgbModule.forRoot(),
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: true}
    ),
    FormsModule,
    HttpModule,
    AlertModule.forRoot(),
    Ng2DragDropModule.forRoot(),
    MdCardModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
