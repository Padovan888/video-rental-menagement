import { HttpClient } from '@angular/common/http';
import { CrudService } from './crud.service';
import { Customer } from 'src/app/shared';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CustomerService extends CrudService<Customer> {
  constructor(protected override readonly http: HttpClient) {
    super(http, 'cliente');
  }

  toggleActivateCustomer(customer: Customer): Observable<Customer> {
    customer.active = !customer.active;
    return this.http.put<Customer>(`${this.apiUrl}`, customer);
  }
}