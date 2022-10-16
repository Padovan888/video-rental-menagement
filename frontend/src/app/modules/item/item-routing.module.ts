import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ItemCrudComponent } from './pages/item-crud/item-crud.component';

const routes: Routes = [
  {
    path: '',
    component: ItemCrudComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ItemRoutingModule {}
