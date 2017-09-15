import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { ReplaySubject } from 'rxjs/ReplaySubject';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Rx';
import { LoginResponse, PasswordChangeRequest, User } from '../models';
import { ApiService } from './api.service';
import { JwtService } from './jwt.service';


@Injectable()
export class UserService {
  private currentUserSubject = new BehaviorSubject<User>(new User());
  public currentUser = this.currentUserSubject.asObservable();

  private isAuthenticatedSubject = new ReplaySubject<boolean>(1);
  public isAuthenticated = this.isAuthenticatedSubject.asObservable();

  constructor(
    private apiService: ApiService,
    private http: HttpClient,
    private jwtService: JwtService
  ) {}

  // Verify if JWT in localstorage with server & load user's info.
  // This runs once on application startup
  populate() {
    if (this.jwtService.getToken()) {
      console.log('is in local');
      this.populateCurrentUser();
    } else {
      console.log('is not in local');
      this.purgeAuth();
    }
  }

  setAuth(token: string) {
    this.jwtService.saveToken(token);
    this.populateCurrentUser();
  }

  purgeAuth() {
    this.jwtService.destroyToken();
    this.currentUserSubject.next(new User());
    this.isAuthenticatedSubject.next(false);
  }

  attemptAuth(credentials): Observable<LoginResponse> {
    return this.apiService.post<LoginResponse>('/auth', credentials)
      .map(data => {
        this.setAuth(data.token);
        return data;
      });
  }

  populateCurrentUser() {
    this.apiService.get('/user')
      .subscribe(
        data => {
          this.currentUserSubject.next(data);
          this.isAuthenticatedSubject.next(true);
        },
        err => this.purgeAuth()
      );
  }

  getCurrentUser(): User {
    return this.currentUserSubject.value;
  }

  update(body: PasswordChangeRequest): Observable<User> {
    return this.apiService
      .put<User>('/users/' + this.currentUserSubject.value.userId, body)
      .map( data => {
        this.purgeAuth();
        return data;
      });
  }

}
