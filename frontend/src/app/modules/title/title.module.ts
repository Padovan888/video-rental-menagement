import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';

import { TitleRoutingModule } from './title-routing.module';
import { TitleCrudComponent } from './pages/title-crud/title-crud.component';
import { NzTabsModule } from 'ng-zorro-antd/tabs';

import { NzTransferModule } from 'ng-zorro-antd/transfer';
import { NzTagModule } from 'ng-zorro-antd/tag';
import { NzDescriptionsModule } from 'ng-zorro-antd/descriptions';
import { NzGridModule } from 'ng-zorro-antd/grid';


@NgModule({
  declarations: [
    TitleCrudComponent
  ],
  imports: [
    CommonModule,
    TitleRoutingModule,
    NzTransferModule,
    NzTabsModule,
    NzTagModule,
    NzDescriptionsModule,
    NzGridModule,
    SharedModule
  ]
})
export class TitleModule { }
