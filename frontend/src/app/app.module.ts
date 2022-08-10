import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';

import { NzCardModule } from 'ng-zorro-antd/card';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    CoreModule,
    AppRoutingModule,
    NzCardModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
