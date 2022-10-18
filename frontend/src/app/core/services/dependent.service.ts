import { HttpClient } from '@angular/common/http';
import { CrudService } from './crud.service';
import { Dependent } from 'src/app/shared';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DependentService extends CrudService<Dependent> {
  constructor(protected override readonly http: HttpClient) {
    super(http, 'dependente');
  }

  toggleActivateDependent(dependent: Dependent): Observable<Dependent> {
    return this.http.patch<Dependent>(`${this.apiUrl}/${dependent.id}`, {});
  }
}