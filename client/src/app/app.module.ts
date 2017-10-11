import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {MdCardModule, MdTooltipModule, MatButtonModule} from '@angular/material';

import {AlertModule} from 'ngx-bootstrap';
import {Ng2DragDropModule} from 'ng2-drag-drop';

import {AppComponent} from './app.component';
import {HeaderComponent} from './header/header.component';
import {LoginComponent} from './login/login.component';
import {HomeComponent} from './home/home.component';
import {FooterComponent} from './footer/footer.component';
import {ManagerCardComponent} from './manager/card/manager-card.component';
import {ManagerComponent} from './manager/manager.component';
import {TeamMemberComponent} from './team-member/team-member.component';
import {TeamMemberCardComponent} from './team-member/card/team-member-card.component';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {BusyModule} from 'angular2-busy';
import {AuthenticationGuardService} from './services/authentication-guard.service';
import {AuthenticationService} from './services/authentication.service';
import {TaskService} from './services/task.service';
import {RankService} from './services/rank.service';

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
    path: 'manager',
    component: ManagerComponent,
    canActivate: [AuthenticationGuardService]
  },
  {
    path: 'team-member',
    component: TeamMemberComponent,
    canActivate: [AuthenticationGuardService]
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
    ManagerCardComponent,
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
    MdCardModule,
    MdTooltipModule,
    MatButtonModule
  ],
  providers: [
    AuthenticationGuardService,
    AuthenticationService,
    TaskService,
    RankService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
