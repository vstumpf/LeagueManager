import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Errors, UserService } from '../shared';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-auth-page',
  templateUrl: './auth.component.html'
})
export class AuthComponent implements OnInit {
  authType: String = '';
  title: String = '';
  errors: Errors = new Errors();
  isSubmitting = false;
  authForm: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService,
    private fb: FormBuilder
  ) {
    this.authForm = this.fb.group({
      'email': ['', Validators.required],
      'password': ['', Validators.required]
    });
  }

  ngOnInit() {
    this.route.url.subscribe(data => {
      // get the last piece of the url
      this.authType = data[data.length - 1].path;
      this.title = (this.authType === 'login') ? 'Sign in' : 'Sign up';
      if (this.authType === 'register') {
        this.authForm.addControl('firstName', new FormControl());
        this.authForm.addControl('lastName', new FormControl());
      }
    });
  }

  submitForm() {
    this.isSubmitting = true;
    this.errors = new Errors();
    const credentials = this.authForm.value;
    this.userService
      .attemptAuth(credentials)
      .subscribe(
        data => this.router.navigateByUrl('/'),
        err => {
          this.errors = err;
          this.isSubmitting = false;
        }
      );
  }
}
