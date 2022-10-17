import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerCrudComponent } from './pages/customer-crud/customer-crud.component';

const routes: Routes = [
  {
    path: '',
    component: CustomerCrudComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CustomerRoutingModule {}
