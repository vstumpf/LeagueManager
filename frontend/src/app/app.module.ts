import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from "@angular/forms";

import { AppComponent } from './app.component';
import { UserDetailComponent } from './user/user-detail.component';
import { UserListComponent } from "./user/user-list.component";
import { UserService } from "./core/user.service";
import { DashboardComponent } from "./dashboard.component";
import { AppRoutingModule } from "./app-routing.module";
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { JWTInterceptor } from "./core/jwt.interceptor";
import { AuthService } from "./core/auth.service";
import { HttpModule } from "@angular/http";
import { CoreModule } from "./core/core.module";

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    UserDetailComponent,
    UserListComponent,
  ],
  imports: [
    BrowserModule,
    CoreModule,
    AppRoutingModule,
  ],
  providers: [  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
