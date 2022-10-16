import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ItemService, ItemTypeService, TitleService, UtilsService } from 'src/app/core';
import { Item, ItemType, Title } from 'src/app/shared';

@Component({
  selector: 'app-item-crud',
  templateUrl: './item-crud.component.html',
  styleUrls: ['./item-crud.component.scss'],
})
export class ItemCrudComponent implements OnInit {
  currentDate: Date = new Date();
  isFetching: boolean = false;
  modalStyle = { top: '2.5rem' };

  itemForm: FormGroup;
  formActionOpened: boolean = false;
  currentAction: 'new' | 'edit' = 'new';

  itemViewed: Item = {};
  viewActionOpened: boolean = false;

  items: Item[] = [];
  titles: Title[] = [];
  itemTypes: ItemType[] = [];

  itemTypeName: string = "";

  constructor(
    private itemService: ItemService,
    private titleService: TitleService,
    private itemTypeService: ItemTypeService,
    private utilsService: UtilsService
  ) {
    this.itemForm = new FormGroup({
      id: new FormControl(null),
      idTitle: new FormControl(null, [Validators.required]),
      idItemType: new FormControl(null, [Validators.required]),
      purchaseDate: new FormControl(null, [Validators.required]),
      serialNumber: new FormControl(null, [
        Validators.required,
        Validators.minLength(10),
        Validators.maxLength(50),
      ]),
    });
  }

  ngOnInit(): void {
    this.fetchItems();
    this.fetchTitles();
    this.fetchItemTypes();
  }

  private fetchItems(): void {
    this.isFetching = true;
    this.itemService.getAll().subscribe({
      next: (items) => {
        this.items = items;
      },
      error: (error) => {
        this.utilsService.showErrorMessage(error.message);
        this.isFetching = false;
      },
      complete: () => (this.isFetching = false),
    });
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

  private fetchItemTypes(): void {
    this.isFetching = true;
    this.itemTypeService.getAll().subscribe({
      next: (itemTypes) => {
        this.itemTypes = itemTypes;
      },
      error: (error) => {
        this.utilsService.showErrorMessage(error.message);
        this.isFetching = false;
      },
      complete: () => (this.isFetching = false),
    });
  }

  handleNewItem(): void {
    this.handleOpenModalForm();
  }

  handleEditItem(item: Item): void {
    this.currentAction = 'edit';
    this.cleanForm();
    this.populateFormFields(item);
    this.handleOpenModalForm();
  }

  handleOpenModalForm(): void {
    this.formActionOpened = true;
  }

  handleCloseModalForm(): void {
    this.cleanForm();
    this.formActionOpened = false;
  }

  handleSubmitItem(): void {
    this.isFetching = true;
    console.log(this.currentAction);
    this.currentAction == 'new' ? this.newItem() : this.editItem();
  }

  private newItem(): void {
    this.itemService.create(this.itemForm.value).subscribe({
      next: (newItem) => this.actionsForSuccess(),
      error: (error) => {
        Object.keys(error.error).forEach((message: string) => {
          this.utilsService.showErrorMessage(error.error[message]);
          this.isFetching = false;
        });
      },
      complete: () => (this.isFetching = false),
    });
  }

  private editItem(): void {
    this.itemService.update(this.itemForm.value).subscribe({
      next: (newItem) => this.actionsForSuccess(newItem),
      error: (error) => {
        Object.keys(error.error).forEach((message: string) => {
          this.utilsService.showErrorMessage(error.error[message]);
          this.isFetching = false;
        });
      },
      complete: () => (this.isFetching = false),
    });
  }

  actionsForSuccess(item?: Item): void {
    this.fetchItems();
    this.utilsService.showSuccessMessage(
      this.currentAction === 'new'
        ? 'Item cadastrado com sucesso'
        : `Item editado com sucesso ${item?.titleModel?.name}`
    );
    this.itemForm.reset();
    this.formActionOpened = false;
  }

  private cleanForm(): void {
    this.itemForm.reset();
  }

  private populateFormFields(item: Item): void {
    this.itemForm.patchValue(item);
    this.itemForm.get('idTitle')?.setValue(item.titleModel?.id);
    this.itemForm.get('idItemType')?.setValue(item.itemTypeModel?.id);
  }

  handleView(item: Item): void {
    this.viewActionOpened = true;
    this.itemViewed = item;
  }

  handleCloseViewModal(): void {
    this.viewActionOpened = false;
  }

  handleDeleteItem(id: number): void {
    this.itemService.delete(id).subscribe({
      next: () => {
        this.utilsService.showSuccessMessage('Excluido com sucesso');
        this.fetchItems();
      },
      error: () => this.utilsService.showErrorMessage('Erro ao excluir o item'),
    });
  }
}
