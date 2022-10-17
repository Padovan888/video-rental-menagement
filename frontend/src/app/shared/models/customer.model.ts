import { Dependent } from './dependent.model';
import { Person } from './person.model';

export class Customer extends Person {
  constructor(
    id?: number,
    name?: string,
    gender?: 'M' | 'F',
    birthDate?: string,
    registrationNumber?: string,
    active?: boolean,
    public cpf?: string,
    public address?: string,
    public telephone?: string,
    public expand?: boolean,
    public dependentModel?: Dependent[]
  ) {
    super(id, name, gender, birthDate, registrationNumber, active);
  }
}
