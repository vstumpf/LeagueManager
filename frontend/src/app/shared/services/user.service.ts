import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs/BehaviorSubject";
import { ReplaySubject } from "rxjs/ReplaySubject";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Rx";
import { User, LoginResponse, PasswordChangeRequest } from "../models";
import { ApiService } from "./api.service";
import { JwtService } from "./jwt.service";


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

  // Verify if JWT in localstorage with server j& load user's info.
  // This runs once on application startup
  populate() {
    if (this.jwtService.getToken()) {
      this.apiService.get('/user')
        .subscribe(
          data => this.setAuth(data.user),
          err => this.purgeAuth()
        );
    } else {
      this.purgeAuth();
    }
  }

  setAuth(user: User) {
    this.jwtService.saveToken(user.token);
    this.currentUserSubject.next(user);
    this.isAuthenticatedSubject.next(true);
  }

  purgeAuth() {
    this.jwtService.destroyToken();
    this.currentUserSubject.next(new User());
    this.isAuthenticatedSubject.next(false);
  }

  attemptAuth(credentials): Observable<User> {
    return this.apiService.post<LoginResponse>('/auth', credentials)
      .lift(data => {
        return this.apiService.get<User>('/users/'+data.userId)
          .map( userData => {
            this.currentUserSubject.next(userData);
            return userData;
          })
      });
  }

  getCurrentUser(): User {
    return this.currentUserSubject.value;
  }

  update(body : PasswordChangeRequest): Observable<User> {
    return this.apiService
      .put<User>('/users/'+ this.currentUserSubject.value.userId, body)
      .map( data => {
        this.purgeAuth();
        return data;
      });
  }

}
