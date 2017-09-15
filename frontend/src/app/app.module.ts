import { ModuleWithProviders, NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AuthModule } from './auth/auth.module';
import { ApiService, FooterComponent, HeaderComponent, JwtService, SharedModule, UserService } from './shared';
import { HomeModule } from './home/home.module';

const rootRouting: ModuleWithProviders = RouterModule.forRoot([]);

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent
  ],
  imports: [
    AuthModule,
    BrowserModule,
    HomeModule,
    rootRouting,
    SharedModule
  ],
  providers: [
    ApiService,
    JwtService,
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
