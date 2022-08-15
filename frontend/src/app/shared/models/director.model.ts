import { BasicModel } from './basic.model';

export class Director extends BasicModel {
  constructor(id?: number, public name?: string) {
    super(id);
  }
}
