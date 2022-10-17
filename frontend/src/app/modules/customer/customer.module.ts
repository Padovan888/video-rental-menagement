import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerRoutingModule } from './customer-routing.module';
import { CustomerCrudComponent } from './pages/customer-crud/customer-crud.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { NzDescriptionsModule } from 'ng-zorro-antd/descriptions';
import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';
import { NzRadioModule } from 'ng-zorro-antd/radio';
import { NzTreeModule } from 'ng-zorro-antd/tree';
import { NzSwitchModule } from 'ng-zorro-antd/switch';
import { NgxMaskModule } from 'ngx-mask';


@NgModule({
  declarations: [
    CustomerCrudComponent
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    NzDescriptionsModule,
    NzDatePickerModule,
    NzRadioModule,
    NzTreeModule,
    NzSwitchModule,
    NgxMaskModule.forRoot(),
    SharedModule
  ]
})
export class CustomerModule { }
