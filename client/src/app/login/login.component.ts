import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Login} from './login.model';
import {AuthenticationService} from '../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  passwordError: boolean;

  constructor(private router: Router, private authenticationService: AuthenticationService) {

  }

  private model = new Login('', '', '');

  ngOnInit() {
    this.authenticationService.logout();
  }

  login() {
    this.authenticationService.login(this.model.username, this.model.password).subscribe(result => {
          if (result !== 'unauthorised') {
              this.router.navigateByUrl(`/${result}`);
          } else {
            this.passwordError = true; // todo handle error logging
          }
    });
  }
}
