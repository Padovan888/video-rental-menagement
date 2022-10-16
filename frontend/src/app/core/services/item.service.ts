import { HttpClient } from '@angular/common/http';
import { CrudService } from './crud.service';
import { Item } from 'src/app/shared';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ItemService extends CrudService<Item> {
  constructor(protected override readonly http: HttpClient) {
    super(http, 'item');
  }
}