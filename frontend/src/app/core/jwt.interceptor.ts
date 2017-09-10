import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs/Observable";
import { AuthService } from "./auth.service";

@Injectable()
export class JWTInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    //const JWT = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhQGEuY29tIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNTA0MzcxNzk2NzU2LCJleHAiOjE1MDQ5NzY1OTZ9.vxAyjgjcV-OpIXlpCTR7PYdjUKpBTKhVpddP_NGxQFG5b-H9uwrfgJRgf7Lf5b1Iz4WPPdYtGJw8QTEZCcB2GQ';

    req = req.clone({
      setHeaders: {
        Authorization: this.authService.token
      }
    });
    return next.handle(req);
  }
}

