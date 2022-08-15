import { HttpClient } from '@angular/common/http';
import { CrudService } from './crud.service';
import { Director } from 'src/app/shared';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class DirectorService extends CrudService<Director> {
  constructor(protected override readonly http: HttpClient) {
    super(http, 'diretor');
  }
}