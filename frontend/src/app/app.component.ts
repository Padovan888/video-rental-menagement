import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  constructor(public router: Router) {}

  title = 'frontend';
  isSiderClosed: boolean = false;

  handleToggleSider(isClosed: boolean): void {
    this.isSiderClosed = isClosed;
  }
}
