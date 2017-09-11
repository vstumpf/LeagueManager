
import { ModuleWithProviders, NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import {
  SharedModule,
  FooterComponent,
  HeaderComponent,
  ApiService,
  JwtService,
  UserService
} from './shared';

const rootRouting: ModuleWithProviders = RouterModule.forRoot([], {useHash: true});

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
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
