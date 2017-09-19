import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {RouterModule, Routes} from "@angular/router";
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';
import {HeaderComponent} from './header/header.component';
import {LoginFormComponent} from './login-form/login.component';
import {HomePageComponent} from './home/home.component';
import {FooterComponent} from './footer/footer.component';

const appRoutes: Routes = [
  {
    path: 'login',
    component: LoginFormComponent
  },
  {
    path: 'home',
    component: HomePageComponent
  },
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  }
]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginFormComponent,
    HomePageComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    NgbModule.forRoot(),
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: true}
    ),
    FormsModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
