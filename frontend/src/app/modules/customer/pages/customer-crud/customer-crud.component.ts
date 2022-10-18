import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CustomerService, DependentService, UtilsService } from 'src/app/core';
import { Customer, Dependent, removeAccents } from 'src/app/shared';
import { Person } from 'src/app/shared/models/person.model';

@Component({
  selector: 'app-customer-crud',
  templateUrl: './customer-crud.component.html',
  styleUrls: ['./customer-crud.component.scss'],
})
export class CustomerCrudComponent implements OnInit {
  currentDate: Date = new Date();
  isFetching: boolean = false;
  modalStyle = { top: '2.5rem' };

  registerDependentAfterRegister: boolean = true;
  customerForm: FormGroup;
  formActionOpened: boolean = false;
  currentAction: 'new' | 'edit' = 'new';
  handlingDependent: boolean = false;

  customerViewed: Customer = {};
  viewActionOpened: boolean = false;
  customerToExpandAfterRefetch: number | undefined;

  customers: Customer[] = [];
  searchCustomer: string = '';

  constructor(
    private customerService: CustomerService,
    private dependentService: DependentService,
    private utilsService: UtilsService
  ) {
    this.customerForm = new FormGroup({
      id: new FormControl(null),
      name: new FormControl(null, [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(50),
      ]),
      birthDate: new FormControl(null, [Validators.required]),
      gender: new FormControl(null, [Validators.required]),
    });
  }

  ngOnInit(): void {
    this.fetchCustomers();
  }

  private fetchCustomers(): void {
    this.isFetching = true;
    this.customerService.getAll().subscribe({
      next: (customers) => {
        this.customers = customers;
        this.customers.forEach((customer) => (customer.expand = false));
        if (this.customerToExpandAfterRefetch) {
          const customerToExpand = this.customers.findIndex(
            (customer) => customer.id === this.customerToExpandAfterRefetch
          );
          this.customers[customerToExpand].expand = true;
          this.customerToExpandAfterRefetch = undefined;
        }
      },
      error: (error) => {
        this.utilsService.showErrorMessage(error.message);
        this.isFetching = false;
      },
      complete: () => (this.isFetching = false),
    });
  }

  handleNewCustomer(): void {
    this.currentAction = 'new';
    this.registerDependentAfterRegister = true;
    this.cleanForm();
    this.setFormToCustomer();
    this.handlingDependent = false;
    this.handleOpenModalForm();
  }

  handleEditCustomer(customer: Customer): void {
    this.currentAction = 'edit';
    this.cleanForm();
    this.setFormToCustomer();
    this.populateFormFields(customer);
    this.handlingDependent = false;
    this.handleOpenModalForm();
  }

  private setFormToCustomer(): void {
    this.customerForm.addControl(
      'cpf',
      new FormControl(null, [Validators.required])
    );
    this.customerForm.addControl(
      'address',
      new FormControl(null, [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(50),
      ])
    );
    this.customerForm.addControl(
      'telephone',
      new FormControl(null, [
        Validators.required,
        Validators.minLength(10),
        Validators.maxLength(11),
      ])
    );
    this.customerForm.removeControl('idCustomer');
  }

  handleToggleActivateCustomer(customer: Customer): void {
    this.customerService.toggleActivateCustomer(customer).subscribe({
      next: () => {
        const customerActiveBeforeUpdate = customer.active;
        customer.active = !customerActiveBeforeUpdate;
        customer.dependentModel?.forEach(
          (dependent) => (dependent.active = !customerActiveBeforeUpdate)
        );
      },
      error: (error) => {
        Object.keys(error.error).forEach((message: string) => {
          this.utilsService.showErrorMessage(error.error[message]);
          this.isFetching = false;
        });
      },
      complete: () => (this.isFetching = false),
    });
  }

  handleDeleteCustomer(id: number): void {
    this.customerService.delete(id).subscribe({
      next: () => {
        this.utilsService.showSuccessMessage('Excluido com sucesso');
        this.fetchCustomers();
      },
      error: () =>
        this.utilsService.showErrorMessage('Erro ao excluir o Cliente'),
    });
  }

  handleNewDependent(customer: Customer): void {
    this.currentAction = 'new';
    this.handlingDependent = true;
    this.cleanForm();
    this.setFormToDependent();
    this.customerForm.get('idCustomer')?.setValue(customer.id);
    this.handleOpenModalForm();
  }

  handleEditDependent(dependent: Dependent): void {
    this.currentAction = 'edit';
    this.handlingDependent = true;
    this.cleanForm();
    this.setFormToDependent();
    this.populateFormFields(dependent);
    this.handleOpenModalForm();
  }

  handleToggleActivateDependent(dependent: Dependent): void {
    this.dependentService.toggleActivateDependent(dependent).subscribe({
      next: () => (dependent.active = !dependent.active),
      error: (error) => {
        Object.keys(error.error).forEach((message: string) => {
          this.utilsService.showErrorMessage(error.error[message]);
          this.isFetching = false;
        });
      },
      complete: () => (this.isFetching = false),
    });
  }

  handleDeleteDependent(dependent: Dependent): void {
    this.dependentService.delete(dependent.id!).subscribe({
      next: () => {
        this.utilsService.showSuccessMessage('Dependente excluido com sucesso');
        this.customerToExpandAfterRefetch = dependent.idCustomer;
        this.fetchCustomers();
      },
      error: () =>
        this.utilsService.showErrorMessage('Erro ao excluir o Dependente'),
    });
  }

  private setFormToDependent(): void {
    this.customerForm.removeControl('cpf');
    this.customerForm.removeControl('address');
    this.customerForm.removeControl('telephone');
    this.customerForm.addControl(
      'idCustomer',
      new FormControl(null, Validators.required)
    );
  }

  handleOpenModalForm(): void {
    this.formActionOpened = true;
  }

  handleCloseModalForm(): void {
    this.cleanForm();
    this.formActionOpened = false;
  }

  handleSubmitCustomer(): void {
    this.isFetching = true;
    if (this.handlingDependent) {
      this.currentAction == 'new' ? this.newDependent() : this.editDependent();
      return;
    }
    this.currentAction == 'new' ? this.newCustomer() : this.editCustomer();
  }

  private newCustomer(): void {
    this.customerService.create(this.customerForm.value).subscribe({
      next: (newCustomer) => {
        this.actionsForSuccess();
        if (this.registerDependentAfterRegister) {
          this.handleNewDependent(newCustomer);
        }
      },
      error: (error) => {
        Object.keys(error.error).forEach((message: string) => {
          this.utilsService.showErrorMessage(error.error[message]);
          this.isFetching = false;
        });
      },
      complete: () => (this.isFetching = false),
    });
  }

  private editCustomer(): void {
    this.customerService.update(this.customerForm.value).subscribe({
      next: (newCustomer) => this.actionsForSuccess(newCustomer),
      error: (error) => {
        Object.keys(error.error).forEach((message: string) => {
          this.utilsService.showErrorMessage(error.error[message]);
          this.isFetching = false;
        });
      },
      complete: () => (this.isFetching = false),
    });
  }

  private newDependent(): void {
    this.dependentService.create(this.customerForm.value).subscribe({
      next: (newDependent) => {
        this.actionsForSuccess();
        this.customerToExpandAfterRefetch = newDependent.idCustomer;
      },
      error: (error) => {
        Object.keys(error.error).forEach((message: string) => {
          this.utilsService.showErrorMessage(error.error[message]);
          this.isFetching = false;
        });
      },
      complete: () => (this.isFetching = false),
    });
  }

  private editDependent(): void {
    this.dependentService.update(this.customerForm.value).subscribe({
      next: (newDependent) => {
        this.actionsForSuccess(newDependent);
        this.customerToExpandAfterRefetch = newDependent.idCustomer;
      },
      error: (error) => {
        Object.keys(error.error).forEach((message: string) => {
          this.utilsService.showErrorMessage(error.error[message]);
          this.isFetching = false;
        });
      },
      complete: () => (this.isFetching = false),
    });
  }

  actionsForSuccess(customer?: Customer): void {
    this.fetchCustomers();
    this.utilsService.showSuccessMessage(
      this.currentAction === 'new'
        ? !this.handlingDependent
          ? 'Cliente cadastrado com sucesso'
          : `Dependente cadastrado com sucesso`
        : !this.handlingDependent
        ? `Cliente editado com sucesso ${customer?.name}`
        : `Dependente editado com sucesso ${customer?.name}`
    );
    this.customerForm.reset();
    this.formActionOpened = false;
  }

  private cleanForm(): void {
    this.customerForm.reset();
  }

  private populateFormFields(person: Person): void {
    this.customerForm.patchValue(person);
  }

  handleView(customer: Customer): void {
    this.viewActionOpened = true;
    this.customerViewed = customer;
  }

  handleCloseViewModal(): void {
    this.viewActionOpened = false;
  }

  get filteredCustomers(): Customer[] {
    return this.customers.filter((customer) =>
      removeAccents(`${customer.name} ${customer.registrationNumber}`)
        .toLocaleLowerCase()
        .indexOf(removeAccents(this.searchCustomer).toLocaleLowerCase()) !== -1
    );
  }
}
