import { Component, OnInit } from "@angular/core";
import { UserService } from "./core/user.service";
import { UserDto } from "./user/user-dto";

@Component({
  selector: 'dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  users: UserDto[] = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getUsers()
      .subscribe(users => this.users = users.slice(0,4));
  }
}
