import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActorService, UtilsService } from 'src/app/core';
import { Actor } from 'src/app/shared';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
})
export class ListComponent implements OnInit {
  isFetching: boolean = false;
  formActionOpened: boolean = false;
  actorForm: FormGroup;
  actors: Actor[] = [];
  editCache: { [key: string]: { edit: boolean; data: Actor } } = {};

  constructor(
    private actorService: ActorService,
    private utilsService: UtilsService
  ) {
    this.actorForm = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(3)]),
    });
  }

  ngOnInit(): void {
    this.fetchActors();
  }

  private fetchActors(): void {
    this.isFetching = true;
    this.actorService.getAll().subscribe({
      next: (actors) => {
        this.actors = actors;
        this.updateEditCache();
      },
      error: (error) => this.utilsService.showErrorMessage(error.message),
      complete: () => (this.isFetching = false),
    });
  }

  updateEditCache(): void {
    this.actors.forEach((item) => {
      this.editCache[item.id!] = {
        edit: false,
        data: { ...item },
      };
    });
  }

  handleOpenModalForm(): void {
    this.formActionOpened = true;
  }

  handleCloseModalForm(): void {
    this.formActionOpened = false;
  }

  handleSubmitActor(): void {
    this.isFetching = true;
    this.actorService.create(this.actorForm.value).subscribe({
      next: (newActor) => this.actionsForSuccess(),
      error: (error) => this.utilsService.showErrorMessage(error.message),
      complete: () => (this.isFetching = false),
    });
  }

  actionsForSuccess(): void {
    this.fetchActors();
    this.utilsService.showSuccessMessage('Ator cadastrado com sucesso');
    this.actorForm.reset();
    this.formActionOpened = false;
  }

  handleDeleteActor(id: number): void {
    this.actorService.delete(id).subscribe({
      next: () => {
        this.utilsService.showSuccessMessage('Excluido com sucesso');
        this.fetchActors();
      },
      error: () => this.utilsService.showErrorMessage('Erro ao excluir o ator'),
    });
  }

  startEdit(id: number): void {
    this.editCache[id].edit = true;
  }

  cancelEdit(id: number): void {
    const index = this.actors.findIndex((item) => item.id == id);
    this.editCache[id] = {
      data: { ...this.actors[index] },
      edit: false,
    };
  }

  saveEdit(id: number): void {
    const index = this.actors.findIndex((item) => item.id === id);
    this.actorService.update(this.editCache[id].data).subscribe({
      next: () => {
        this.utilsService.showSuccessMessage(
          `${this.editCache[id].data.name} alterado com sucesso`
        );
        Object.assign(this.actors[index], this.editCache[id].data);
        this.editCache[id].edit = false;
      },
      error: () => {
        this.utilsService.showErrorMessage('Erro ao Alterar o Ator');
        Object.assign(this.editCache[id].data, this.actors[index]);
        this.editCache[id].edit = false;
      },
    });
  }
}
