import { Title } from './title.model';
import { BasicModel } from './basic.model';
import { ItemType } from './item-type.model';

export class Item extends BasicModel {
  constructor(
    id?: number,
    public serialNumber?: string,
    public titleModel?: Title,
    public purchaseDate?: string,
    public itemTypeModel?: ItemType,
  ) {
    super(id);
  }
}