import { Injectable } from "@angular/core";
import { environment } from "../../../environments/environment";

@Injectable()
export class JwtService {

  getToken(): String {
    return window.localStorage[`${environment.token}`];
  }

  saveToken(token: String) {
    window.localStorage[`${environment.token}`] = token;
  }

  destroyToken() {
    window.localStorage.removeItem(`${environment.token}`);
  }
}
