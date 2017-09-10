import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { UserDto } from "../user/user-dto";
import { AuthService } from "./auth.service";

@Injectable()
export class UserService {

  private usersUrl = 'api/users';
  private currentUser : UserDto;

  constructor(private http: HttpClient, private authService: AuthService) {
    this.currentUser = null;
  }

  getUsers(): Observable<UserDto[]>{
    return this.http.get<UserDto[]>(this.usersUrl);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

  getUser(id: number) : Observable<UserDto> {
    const url = `${this.usersUrl}/${id}`;
    return this.http.get<UserDto>(url);
  }

  getCurrentUser() : UserDto {
    return this.currentUser;
  }
}
