import { HttpClient } from '@angular/common/http';
import { CrudService } from './crud.service';
import { ItemType } from 'src/app/shared';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ItemTypeService extends CrudService<ItemType> {
  constructor(protected override readonly http: HttpClient) {
    super(http, 'tipoitem');
  }
}