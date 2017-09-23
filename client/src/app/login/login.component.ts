import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Login} from './login.model';
import {Http, URLSearchParams} from '@angular/http';
import {getResponseURL} from "@angular/http/src/http_utils";
import {error} from "util";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, private http: Http) {
  }

  private model = new Login("","");

  ngOnInit() {
  }

  login() {
    this.authenticate();
  }

  private authenticate(): void {
    let params: URLSearchParams = new URLSearchParams();
    params.set("username", this.model.username);
    params.set("password", this.model.password);
    this.http.get("http://localhost:8080/login", {search : params} ).subscribe(
      (response) => {
        if (response.ok) {
          this.router.navigateByUrl("/tasks");
        }
      },
      (error) => alert("Password is wrong!"),
      () => console.log("Complete")
    );
  }
}
