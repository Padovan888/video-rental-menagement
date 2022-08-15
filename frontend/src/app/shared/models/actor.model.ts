import { BasicModel } from './basic.model';
export class Actor extends BasicModel {
  constructor(id?: number, public name?: string) {
    super(id);
  }
}
