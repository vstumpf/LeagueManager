import { Component, OnInit } from '@angular/core';

import { User } from "./user";
import { UserService } from "./user.service";
import { Router } from "@angular/router";


@Component({
  selector: 'users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'],
  providers: []
})
export class UsersComponent implements OnInit {
  school = 'Cal Poly SLO';
  users: User[];
  selectedUser : User;

  constructor(
    private router: Router,
    private userService: UserService
    ) { }

  getUsers(): void {
    //this.userService.getUsers().then(users => this.users = users);
    this.userService.getUsers().subscribe(users => {this.users = users; console.log(this.users);});
  }

  ngOnInit(): void {
    this.getUsers();
  }

  onSelect(user: User): void {
    this.selectedUser = user;
  }

  gotoDetail(): void {
    this.router.navigate(['/users', this.selectedUser.id]);
  }
}

