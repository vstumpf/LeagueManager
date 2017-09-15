import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { ShowAuthedDirective } from './show-authed.directive';
import { ListErrorsComponent } from './list-errors.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ListErrorsPipe } from './pipes';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule
  ],
  declarations: [
    ShowAuthedDirective,
    ListErrorsComponent,
    ListErrorsPipe,
  ],
  exports: [
    HttpClientModule,
    RouterModule,
    ShowAuthedDirective,
    CommonModule,
    ListErrorsComponent,
    ListErrorsPipe,
    FormsModule,
    ReactiveFormsModule,
  ]
})
export class SharedModule {}
