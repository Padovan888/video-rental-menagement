import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ActorRoutingModule } from './actor-routing.module';
import { ListComponent } from './pages/list/list.component';
import { SharedModule } from 'src/app/shared/shared.module';

@NgModule({
  declarations: [ListComponent],
  imports: [CommonModule, ActorRoutingModule, SharedModule],
})
export class ActorModule {}
