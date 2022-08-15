import { HttpClient } from '@angular/common/http';
import { CrudService } from './crud.service';
import { Class } from 'src/app/shared';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ClassService extends CrudService<Class> {
  constructor(protected override readonly http: HttpClient) {
    super(http, 'classe');
  }
}