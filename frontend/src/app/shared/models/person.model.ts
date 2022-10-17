import { BasicModel } from './basic.model';

export abstract class Person extends BasicModel {
  constructor(
    id?: number,
    public name?: string,
    public gender?: 'M' | 'F',
    public birthDate?: string,
    public readonly registrationNumber?: string,
    public active?: boolean
  ) {
    super(id);
  }
}
