import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DirectorCrudComponent } from './pages/director-crud/director-crud.component';

const routes: Routes = [
  {
    path: '',
    component: DirectorCrudComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DirectorRoutingModule { }
