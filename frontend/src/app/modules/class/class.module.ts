import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClassRoutingModule } from './class-routing.module';
import { ClassCrudComponent } from './pages/class-crud/class-crud.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { NgxCurrencyModule } from "ngx-currency";

@NgModule({
  declarations: [
    ClassCrudComponent
  ],
  imports: [
    CommonModule,
    ClassRoutingModule,
    NgxCurrencyModule,
    SharedModule
  ]
})
export class ClassModule { }
