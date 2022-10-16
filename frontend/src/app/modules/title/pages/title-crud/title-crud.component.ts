import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TransferItem } from 'ng-zorro-antd/transfer';
import {
  ActorService,
  ClassService,
  DirectorService,
  TitleService,
  UtilsService,
} from 'src/app/core';
import { Actor, Class, Director, Title } from 'src/app/shared';

@Component({
  selector: 'app-title-crud',
  templateUrl: './title-crud.component.html',
  styleUrls: ['./title-crud.component.scss'],
})
export class TitleCrudComponent implements OnInit {
  currentDate: Date = new Date();
  isFetching: boolean = false;
  modalStyle = { top: '2.5rem' };

  titleForm: FormGroup;
  formActionOpened: boolean = false;
  currentAction: 'new' | 'edit' = 'new';

  titleViewed: Title = {};
  viewActionOpened: boolean = false;

  titles: Title[] = [];
  directors: Director[] = [];
  classes: Class[] = [];
  actors: Actor[] = [];
  transferActors: Array<TransferItem> = [];

  constructor(
    private titleService: TitleService,
    private classService: ClassService,
    private actorService: ActorService,
    private directorService: DirectorService,
    private utilsService: UtilsService
  ) {
    this.titleForm = new FormGroup({
      id: new FormControl(null),
      name: new FormControl(null, [
        Validators.required,
        Validators.minLength(3),
      ]),
      idDirector: new FormControl(null, [Validators.required]),
      idClass: new FormControl(null, [Validators.required]),
      category: new FormControl(null, [Validators.required]),
      synopsis: new FormControl(null, [
        Validators.required,
        Validators.minLength(10),
      ]),
      year: new FormControl(null, [Validators.required]),
    });
  }

  ngOnInit(): void {
    this.fetchTitles();
    this.fetchDirectors();
    this.fetchClasses();
    this.fetchActors();
  }

  private fetchTitles(): void {
    this.isFetching = true;
    this.titleService.getAll().subscribe({
      next: (titles) => {
        this.titles = titles;
      },
      error: (error) => {
        this.utilsService.showErrorMessage(error.message);
        this.isFetching = false;
      },
      complete: () => (this.isFetching = false),
    });
  }

  private fetchDirectors(): void {
    this.isFetching = true;
    this.directorService.getAll().subscribe({
      next: (directors) => {
        this.directors = directors;
      },
      error: (error) => {
        this.utilsService.showErrorMessage(error.message);
        this.isFetching = false;
      },
      complete: () => (this.isFetching = false),
    });
  }

  private fetchClasses(): void {
    this.isFetching = true;
    this.classService.getAll().subscribe({
      next: (classes) => {
        this.classes = classes;
      },
      error: (error) => {
        this.utilsService.showErrorMessage(error.message);
        this.isFetching = false;
      },
      complete: () => (this.isFetching = false),
    });
  }

  private fetchActors(): void {
    this.isFetching = true;
    this.actorService.getAll().subscribe({
      next: (actors) => {
        this.actors = actors;
        this.initActorsList();
      },
      error: (error) => {
        this.utilsService.showErrorMessage(error.message);
        this.isFetching = false;
      },
      complete: () => (this.isFetching = false),
    });
  }

  private initActorsList(): void {
    this.transferActors = [];
    this.actors.forEach((actor) => {
      this.transferActors.push({
        key: actor.id,
        title: actor.name!,
        direction: 'left',
      });
    });
  }

  handleNewTitle(): void {
    this.handleOpenModalForm();
  }

  handleEditTitle(title: Title): void {
    this.currentAction = 'edit';
    this.cleanForm();
    this.populateFormFields(title);
    this.handleOpenModalForm();
  }

  handleOpenModalForm(): void {
    this.formActionOpened = true;
  }

  handleCloseModalForm(): void {
    this.cleanForm();
    this.formActionOpened = false;
  }

  handleSubmitTitle(): void {
    this.isFetching = true;
    this.currentAction == 'new' ? this.newTitle() : this.editTitle();
  }

  private newTitle(): void {
    this.titleService
      .create({ ...this.titleForm.value, actorIds: this.getSelectedActors() })
      .subscribe({
        next: (newTitle) => this.actionsForSuccess(),
        error: (error) => {
          Object.keys(error.error).forEach((message: string) => {
            this.utilsService.showErrorMessage(error.error[message]);
            this.isFetching = false;
          });
        },
        complete: () => (this.isFetching = false),
      });
  }

  private editTitle(): void {
    this.titleService
      .update({ ...this.titleForm.value, actorIds: this.getSelectedActors() })
      .subscribe({
        next: (newTitle) => this.actionsForSuccess(newTitle),
        error: (error) => {
          Object.keys(error.error).forEach((message: string) => {
            this.utilsService.showErrorMessage(error.error[message]);
            this.isFetching = false;
          });
        },
        complete: () => (this.isFetching = false),
      });
  }

  getSelectedActors(): Actor[] {
    const selectedActors = this.transferActors.filter(
      (transferItem) => transferItem.direction === 'right'
    );
    return selectedActors.map((transferItem) => {
      return { id: transferItem['key'], name: transferItem['title'] };
    });
  }

  actionsForSuccess(title?: Title): void {
    this.fetchTitles();
    this.utilsService.showSuccessMessage(
      this.currentAction === 'new'
        ? 'Título cadastrado com sucesso'
        : `Título editado com sucesso ${title?.name}`
    );
    this.titleForm.reset();
    this.formActionOpened = false;
  }

  private cleanForm(): void {
    this.titleForm.reset();
    this.initActorsList();
  }

  private populateFormFields(title: Title): void {
    this.titleForm.patchValue(title);
    this.titleForm.get('idDirector')?.setValue(title.directorModel?.id);
    this.titleForm.get('idClass')?.setValue(title.classModel?.id);
    this.transferActors.forEach((actor) => {
      if (
        title.actorsModel?.find((findedActor) => findedActor.id == actor['key'])
      ) {
        actor.direction = 'right';
      }
    });
  }

  handleView(title: Title): void {
    this.viewActionOpened = true;
    this.titleViewed = title;
  }

  handleCloseViewModal(): void {
    this.viewActionOpened = false;
  }

  handleDeleteTitle(id: number): void {
    this.titleService.delete(id).subscribe({
      next: () => {
        this.utilsService.showSuccessMessage('Excluido com sucesso');
        this.fetchTitles();
      },
      error: () =>
        this.utilsService.showErrorMessage('Erro ao excluir o título'),
    });
  }

  filterOption(inputValue: string, item: TransferItem): boolean {
    return (
      item.title.toLocaleLowerCase().indexOf(inputValue.toLowerCase()) > -1
    );
  }

  getActorsName(title: Title): string {
    const actorsNameArray = title.actorsModel?.map((actor) => actor.name);
    return actorsNameArray!.join(', ');
  }
}
