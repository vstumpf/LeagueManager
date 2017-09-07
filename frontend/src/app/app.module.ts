import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from "@angular/forms";

import { AppComponent } from './app.component';
import { UserDetailComponent } from './user-detail.component';
import { UsersComponent } from "./users.component";
import { UserService } from "./user.service";
import { DashboardComponent } from "./dashboard.component";
import { AppRoutingModule } from "./app-routing.module";
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { JWTInterceptor } from "./jwt.interceptor";
import { AuthService } from "./auth.service";
import { HttpModule } from "@angular/http";

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    UserDetailComponent,
    UsersComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [
    AuthService,
    UserService,
    { provide: HTTP_INTERCEPTORS, useClass: JWTInterceptor, multi: true }

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
