import { HttpClient } from '@angular/common/http';
import { CrudService } from './crud.service';
import { Actor } from 'src/app/shared';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ActorService extends CrudService<Actor> {
  constructor(protected override readonly http: HttpClient) {
    super(http, 'ator');
  }
}