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

  toggleActivateDependent(customer: Dependent): Observable<Dependent> {
    customer.active = !customer.active;
    return this.http.put<Dependent>(`${this.apiUrl}`, customer);
  }
}