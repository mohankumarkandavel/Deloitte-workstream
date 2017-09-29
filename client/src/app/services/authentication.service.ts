import {Injectable} from "@angular/core";
import {Http, URLSearchParams} from "@angular/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class AuthenticationService {

  private authUrl = 'http://localhost:8080/login';

  constructor(private http: Http) {
  }

  login(username: string, password: string) : Observable<string> {
    let params: URLSearchParams = new URLSearchParams();
    params.set("username", username);
    params.set("password", password);

    return this.http.get(this.authUrl, {search: params})
      .map((response) => {
        if (response.ok) {
          let role = response.headers.get("role");
          localStorage.setItem("role", role);
          let userId = JSON.parse(response.text()).id;
          localStorage.setItem("userId", userId);
          return role;
        }
      },
    ).catch((error:any) => {
      if (error.status === 401) {
        return Observable.of('unauthorised');
      } else if(error.status === 400) {
        return Observable.of('Bad request');
      } else {
        return Observable.throw('Server error');
      }
    });
  }

  logout() {
    localStorage.clear();
  }

  isManager() {
    return localStorage.getItem('role') === 'manager';
  }

  isTeamMember() {
    return localStorage.getItem('role') === 'team-member';
  }
}
