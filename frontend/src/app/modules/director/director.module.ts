import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DirectorRoutingModule } from './director-routing.module';
import { DirectorCrudComponent } from './pages/director-crud/director-crud.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [
    DirectorCrudComponent
  ],
  imports: [
    CommonModule,
    DirectorRoutingModule,
    SharedModule
  ]
})
export class DirectorModule { }
