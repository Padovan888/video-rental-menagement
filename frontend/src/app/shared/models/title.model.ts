import { Actor } from './actor.model';
import { BasicModel } from './basic.model';
import { Class } from './class.model';
import { Director } from './director.model';

export class Title extends BasicModel {
  constructor(
    id?: number,
    public actorsModel?: Actor[],
    public actorIds?: Actor[],
    public name?: string,
    public category?: string,
    public idClass?: number,
    public classModel?: Class,
    public idDirector?: number,
    public directorModel?: Director,
    public synopsis?: string,
    public year?: string
  ) {
    super(id);
  }
}
