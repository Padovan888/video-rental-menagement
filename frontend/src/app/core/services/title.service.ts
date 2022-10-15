import { HttpClient } from '@angular/common/http';
import { CrudService } from './crud.service';
import { Title } from 'src/app/shared';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class TitleService extends CrudService<Title> {
  constructor(protected override readonly http: HttpClient) {
    super(http, 'titulo');
  }
}