import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ApiService, UserService } from '../shared';

@Component({
  selector: 'app-home-page',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  constructor(
    private router: Router,
    private userService: UserService,
    private apiService: ApiService
  ) {}

  isAuthenticated: boolean;

  ngOnInit() {
    console.log('oninit');
    this.userService.isAuthenticated.subscribe(
      (authenticated) => {
        this.isAuthenticated = authenticated;
        if (authenticated) {
          this.getPeople();
        }
      }
    );
  }

  getPeople() {
    this.apiService.get('/users').subscribe(
      (data) => {
        console.log(data);
      }
    );
  }


}
