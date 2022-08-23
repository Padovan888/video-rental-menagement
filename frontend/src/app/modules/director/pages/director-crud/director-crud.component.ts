import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DirectorService, UtilsService } from 'src/app/core';
import { Director } from 'src/app/shared';

@Component({
  selector: 'app-director-crud',
  templateUrl: './director-crud.component.html',
  styleUrls: ['./director-crud.component.scss'],
})
export class DirectorCrudComponent implements OnInit {
  isFetching: boolean = false;
  formActionOpened: boolean = false;
  directorForm: FormGroup;
  directors: Director[] = [];
  editCache: { [key: string]: { edit: boolean; data: Director } } = {};

  constructor(
    private directorService: DirectorService,
    private utilsService: UtilsService
  ) {
    this.directorForm = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(3)]),
    });
  }

  ngOnInit(): void {
    this.fetchDirectors();
  }

  private fetchDirectors(): void {
    this.isFetching = true;
    this.directorService.getAll().subscribe({
      next: (directors) => {
        this.directors = directors;
        this.updateEditCache();
      },
      error: (error) => {
        this.utilsService.showErrorMessage(error.message);
        this.isFetching = false;
      },
      complete: () => (this.isFetching = false),
    });
  }

  updateEditCache(): void {
    this.directors.forEach((item) => {
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
    this.directorForm.reset();
    this.formActionOpened = false;
  }

  handleSubmitDirector(): void {
    this.isFetching = true;
    this.directorService.create(this.directorForm.value).subscribe({
      next: (newDirector) => this.actionsForSuccess(),
      error: (error) => {
        this.utilsService.showErrorMessage(error.message);
        this.isFetching = false;
      },
      complete: () => (this.isFetching = false),
    });
  }

  actionsForSuccess(): void {
    this.fetchDirectors();
    this.utilsService.showSuccessMessage('Diretor cadastrado com sucesso');
    this.directorForm.reset();
    this.formActionOpened = false;
  }

  handleDeleteDirector(id: number): void {
    this.directorService.delete(id).subscribe({
      next: () => {
        this.utilsService.showSuccessMessage('Excluido com sucesso');
        this.fetchDirectors();
      },
      error: () =>
        this.utilsService.showErrorMessage('Erro ao excluir o diretor'),
    });
  }

  startEdit(id: number): void {
    this.editCache[id].edit = true;
  }

  cancelEdit(id: number): void {
    const index = this.directors.findIndex((item) => item.id == id);
    this.editCache[id] = {
      data: { ...this.directors[index] },
      edit: false,
    };
  }

  saveEdit(id: number): void {
    const index = this.directors.findIndex((item) => item.id === id);
    this.directorService.update(this.editCache[id].data).subscribe({
      next: () => {
        this.utilsService.showSuccessMessage(
          `${this.editCache[id].data.name} alterado com sucesso`
        );
        Object.assign(this.directors[index], this.editCache[id].data);
        this.editCache[id].edit = false;
      },
      error: () => {
        this.utilsService.showErrorMessage('Erro ao Alterar o Diretor');
        Object.assign(this.editCache[id].data, this.directors[index]);
        this.editCache[id].edit = false;
      },
    });
  }
}
