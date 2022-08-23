import { BasicModel } from './basic.model';
export class Class extends BasicModel {
  constructor(
    id?: number,
    public name?: string,
    public returnPeriod?: number,
    public value?: number
  ) {
    super(id);
  }
}
