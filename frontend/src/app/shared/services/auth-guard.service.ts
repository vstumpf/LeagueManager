import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot } from '@angular/router';
import { UserService } from './user.service';
import { Observable } from 'rxjs/Rx';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private userService: UserService) {
  }

  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): Observable<boolean> {
    return this.userService.isAuthenticated.take(1);
  }
}
