import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ClassService, UtilsService } from 'src/app/core';
import { Class } from 'src/app/shared';

@Component({
  selector: 'app-list',
  templateUrl: './class-crud.component.html',
  styleUrls: ['./class-crud.component.scss'],
})
export class ClassCrudComponent implements OnInit {
  isFetching: boolean = false;
  formActionOpened: boolean = false;
  classForm: FormGroup;
  classes: Class[] = [];
  editCache: { [key: string]: { edit: boolean; data: Class } } = {};

  constructor(
    private classService: ClassService,
    private utilsService: UtilsService
  ) {
    this.classForm = new FormGroup({
      name: new FormControl(null, [
        Validators.required,
        Validators.minLength(3),
      ]),
      returnPeriod: new FormControl(null, [Validators.required]),
      value: new FormControl(null, [Validators.required]),
    });
  }

  ngOnInit(): void {
    this.fetchClasses();
  }

  private fetchClasses(): void {
    this.isFetching = true;
    this.classService.getAll().subscribe({
      next: (classs) => {
        this.classes = classs;
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
    this.classes.forEach((item) => {
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
    this.classForm.reset();
    this.formActionOpened = false;
  }

  handleSubmitClass(): void {
    this.isFetching = true;
    this.classService.create(this.classForm.value).subscribe({
      next: (newClass) => this.actionsForSuccess(),
      error: (error) => {
        console.log(error.error);
        Object.keys(error.error).forEach((message: string) => {
          this.utilsService.showErrorMessage(error.error[message]);
          this.isFetching = false;
        });
      },
      complete: () => (this.isFetching = false),
    });
  }

  actionsForSuccess(): void {
    this.fetchClasses();
    this.utilsService.showSuccessMessage('Classe cadastrada com sucesso');
    this.classForm.reset();
    this.formActionOpened = false;
  }

  handleDeleteClass(id: number): void {
    this.classService.delete(id).subscribe({
      next: () => {
        this.utilsService.showSuccessMessage('Excluida com sucesso');
        this.fetchClasses();
      },
      error: () =>
        this.utilsService.showErrorMessage('Erro ao excluir a classe'),
    });
  }

  startEdit(id: number): void {
    this.editCache[id].edit = true;
  }

  cancelEdit(id: number): void {
    const index = this.classes.findIndex((item) => item.id == id);
    this.editCache[id] = {
      data: { ...this.classes[index] },
      edit: false,
    };
  }

  saveEdit(id: number): void {
    const index = this.classes.findIndex((item) => item.id === id);
    this.classService.update(this.editCache[id].data).subscribe({
      next: () => {
        this.utilsService.showSuccessMessage(
          `${this.editCache[id].data.name} alterada com sucesso`
        );
        Object.assign(this.classes[index], this.editCache[id].data);
        this.editCache[id].edit = false;
      },
      error: () => {
        this.utilsService.showErrorMessage('Erro ao Alterar a Classe');
        Object.assign(this.editCache[id].data, this.classes[index]);
        this.editCache[id].edit = false;
      },
    });
  }
}
