import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { FooterComponent } from './footer/footer.component';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzMessageModule } from 'ng-zorro-antd/message';


@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    BrowserModule,
    BrowserAnimationsModule,
    NzLayoutModule,
    NzIconModule,
    NzMessageModule
  ],
  declarations: [
    FooterComponent,
  ],
  exports: [
    HttpClientModule,
    BrowserModule,
    BrowserAnimationsModule,

    FooterComponent,
  ],
})
export class CoreModule {}
