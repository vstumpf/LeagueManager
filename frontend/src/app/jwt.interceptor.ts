import {
  HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HTTP_INTERCEPTORS
} from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UserService } from "./user.service";
import { Observable } from "rxjs/Observable";

@Injectable()
export class JWTInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const JWT = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhQGEuY29tIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNTA0MzcxNzk2NzU2LCJleHAiOjE1MDQ5NzY1OTZ9.vxAyjgjcV-OpIXlpCTR7PYdjUKpBTKhVpddP_NGxQFG5b-H9uwrfgJRgf7Lf5b1Iz4WPPdYtGJw8QTEZCcB2GQ';

    req = req.clone({
      setHeaders: {
        Authorization: JWT
      }
    });
    return next.handle(req);
  }
}

