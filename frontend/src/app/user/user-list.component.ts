import { Component, OnInit } from '@angular/core';

import { UserDto } from "./user-dto";
import { UserService } from "../core/user.service";
import { Router } from "@angular/router";


@Component({
  selector: 'user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
  providers: []
})
export class UserListComponent implements OnInit {
  school = 'Cal Poly SLO';
  users: UserDto[];
  selectedUser : UserDto;

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

  onSelect(user: UserDto): void {
    this.selectedUser = user;
  }

  gotoDetail(): void {
    this.router.navigate(['/users', this.selectedUser.id]);
  }
}

