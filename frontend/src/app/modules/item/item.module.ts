import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ItemRoutingModule } from './item-routing.module';
import { ItemCrudComponent } from './pages/item-crud/item-crud.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { NzTabsModule } from 'ng-zorro-antd/tabs';
import { NzTagModule } from 'ng-zorro-antd/tag';
import { NzDescriptionsModule } from 'ng-zorro-antd/descriptions';
import { NzGridModule } from 'ng-zorro-antd/grid';
import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';

@NgModule({
  declarations: [
    ItemCrudComponent
  ],
  imports: [
    CommonModule,
    ItemRoutingModule,
    NzTabsModule,
    NzTagModule,
    NzDescriptionsModule,
    NzGridModule,
    NzDatePickerModule,
    SharedModule
  ]
})
export class ItemModule { }
