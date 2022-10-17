import { Customer } from './customer.model';
import { Person } from './person.model';

export class Dependent extends Person {
  constructor(
    id?: number,
    name?: string,
    gender?: 'M' | 'F',
    birthdate?: string,
    registrationNumber?: string,
    active?: boolean,
    public idCustomer?: number,
    public customerModel?: Customer,
  ) {
    super(id, name, gender, birthdate, registrationNumber, active);
  }
}
