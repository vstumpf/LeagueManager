import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { JwtService } from './jwt.service';
import { Observable } from 'rxjs/Rx';
import { environment } from '../../../environments/environment';

@Injectable()
export class ApiService {
  constructor(
    private http: HttpClient,
    private jwtService: JwtService
  ) {}

  private setHeaders(): HttpHeaders {
    const headersConfig = {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    };

    if (this.jwtService.getToken()) {
      headersConfig['Authorization'] = this.jwtService.getToken();
    }
    return new HttpHeaders(headersConfig);
  }

  private formatErrors(error: any) {
    return Observable.throw(error.json());
  }

  get<t>(path: string, params: HttpParams = new HttpParams()): Observable<any> {
    return this.http.get<t>(
      `${environment.api_url}${path}`,
      { headers: this.setHeaders(), params: params });
  }

  put<t>(path: string, body: Object = {}): Observable<any> {
    return this.http.put<t>(
      `${environment.api_url}${path}`,
      body,
      { headers: this.setHeaders() }
    ).catch(this.formatErrors);
  }

  post<t>(path: string, body: Object = {}): Observable<any> {
    return this.http.post<t>(
      `${environment.api_url}${path}`,
      body,
      { headers: this.setHeaders() }
    ).catch(this.formatErrors);
  }

  delete<t>(path: string): Observable<any> {
    return this.http.delete<t>(
      `${environment.api_url}${path}`,
      {headers: this.setHeaders()}
    ).catch(this.formatErrors);
  }
}
