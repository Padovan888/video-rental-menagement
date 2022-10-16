import { BasicModel } from './basic.model';

export class ItemType extends BasicModel {
  constructor(id?: number, public name?: string) {
    super(id);
  }
}
