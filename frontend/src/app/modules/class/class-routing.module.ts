import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClassCrudComponent } from './pages/class-crud/class-crud.component';

const routes: Routes = [
  {
    path: '',
    component: ClassCrudComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClassRoutingModule { }
