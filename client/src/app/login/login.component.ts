import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Login} from '../models/login.model';
import {AuthenticationService} from '../services/authentication.service';
import { TaskService } from '../services/task.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  private passwordError = false;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private taskService: TaskService
  ) {}

  private model = new Login('', '', '');

  ngOnInit() {
    this.authenticationService.logout();
    this.taskService.clearTasks();
  }

  login() {
    this.authenticationService.login(this.model.username, this.model.password).subscribe(result => {
          if (result !== 'unauthorised') {
            this.passwordError = false;
            this.router.navigateByUrl(`/${result}`);
          } else {
            this.passwordError = true;
          }
    },
      (error => console.log(error.toString())));
  }
}
