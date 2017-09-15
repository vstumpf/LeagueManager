import { Component, Input } from '@angular/core';

import { Errors } from './models';

@Component({
  selector: 'part-list-errors',
  templateUrl: './list-errors.component.html'
})
export class ListErrorsComponent {
  errorList: Errors;

  @Input()
  set errors(errorList: Errors) {
    this.errorList = errorList;
  }

  get errors() {
    return this.errorList;
  }
}
