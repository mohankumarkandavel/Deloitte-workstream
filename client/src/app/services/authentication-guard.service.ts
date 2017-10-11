import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router';
import {AuthenticationService} from './authentication.service';

@Injectable()
export class AuthenticationGuardService implements CanActivate {
  constructor(private authService: AuthenticationService) {

  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.authService.isManager()) {
      return route.url[route.url.length - 1].toString() === 'manager';
    } else {
      return route.url[route.url.length - 1].toString() === 'team-member';
    }
  }
}
