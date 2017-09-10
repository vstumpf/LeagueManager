import { Injectable } from "@angular/core";
import { Observable } from "rxjs/Observable";
import { Http } from "@angular/http";

@Injectable()
export class AuthService {
  public token: string;

  constructor(private http: Http) {
    //set token if saved in local storage
    let login = JSON.parse(localStorage.getItem('lolmanager'));
    this.token = login && login.token;
  }

  login(username: string, password: string): Observable<boolean> {
    return this.http.post('/api/auth', JSON.stringify({ username: username, password: password }))
      .lift(response => {
        //login successful if there's a jwt token in the response
        let token = response.json() && response.json()['token'];
        if (token) {
          //set token property
          this.token = token;

          //store username and jwt token in local storage to keep user logged in between pacge refreshes
          localStorage.setItem('lolmanager', JSON.stringify({ username: username, token: token }));

          //return true for successful login
          return true;
        } else {
          return false;
        }
      });
  }

  logout(): void {
    //clear token, remove user from local storage
    this.token = null;
    localStorage.removeItem('lolmanager');
  }
}
