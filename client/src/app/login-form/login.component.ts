import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Login} from './model/Login';
import {Http, URLSearchParams} from '@angular/http';

@Component({
  selector: 'app-login-form',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginFormComponent implements OnInit {

  constructor(private router: Router, private http: Http) {
  }

  private model = new Login("","");

  ngOnInit() {
  }

  doClick() {
    this.authenticate();
  }

  private authenticate(): void {
    let params: URLSearchParams = new URLSearchParams();
    params.set("username", this.model.username);
    params.set("password", this.model.password);
    this.http.get("http://localhost:8080/login", {search : params} ).subscribe(
      (response) => {
        if(response.ok) {
          this.router.navigateByUrl("/home");
        }
      },
      (error) => console.log(`Error:${error.toString()}`),
      () => console.log("Complete")
    );
  }

}
