import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TitleCrudComponent } from './pages/title-crud/title-crud.component';

const routes: Routes = [
  {
    path: '',
    component: TitleCrudComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TitleRoutingModule { }
