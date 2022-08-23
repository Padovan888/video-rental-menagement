import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ActorRoutingModule } from './actor-routing.module';
import { ActorCrudComponent } from './pages/actor-crud/actor-crud.component';
import { SharedModule } from 'src/app/shared/shared.module';

@NgModule({
  declarations: [ActorCrudComponent],
  imports: [CommonModule, ActorRoutingModule, SharedModule],
})
export class ActorModule {}
