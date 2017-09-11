import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { ShowAuthedDirective } from './show-authed.directive';

@NgModule({
  imports: [
    HttpClientModule,
    RouterModule
  ],
  declarations: [
    ShowAuthedDirective
  ],
  exports: [
    HttpClientModule,
    RouterModule,
    ShowAuthedDirective
  ]
})
export class SharedModule {}
