import { Pipe, PipeTransform } from '@angular/core';
import { Error } from '../models';

@Pipe({ name: 'errorFormat' })
export class ListErrorsPipe implements PipeTransform {
  transform(value: Error): string {
    return value.error + ' | ' + value.message;
  }
}
