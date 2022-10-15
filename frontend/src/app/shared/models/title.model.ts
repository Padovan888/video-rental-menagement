import { Actor } from './actor.model';
import { BasicModel } from './basic.model';

export class Title extends BasicModel {
  constructor(
    id?: number,
    public actorIds?: Actor[],
    public name?: string,
    public category?: string,
    public idClass?: number,
    public idDirector?: number,
    public synopsis?: string,
    public year?: string
  ) {
    super(id);
  }
}
