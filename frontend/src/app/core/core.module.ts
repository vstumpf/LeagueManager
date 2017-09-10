
import { NgModule, Optional, SkipSelf } from "@angular/core";
import { TitleComponent } from "./title.component";
import { UserService } from "./user.service";
import { AuthService } from "./auth.service";
import { HTTP_INTERCEPTORS } from "@angular/common/http";
import { JWTInterceptor } from "./jwt.interceptor";

@NgModule({
  imports: [],
  declarations: [ TitleComponent ],
  exports: [ TitleComponent ],
  providers: [
    AuthService,
    UserService,
    { provide: HTTP_INTERCEPTORS, useClass: JWTInterceptor, multi: true }
  ]
})
export class CoreModule {
  constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error('CoreModule already loaded. Import it in the AppModule only');
    }
  }
}
