import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend';
  isSiderClosed: boolean = false;

  handleToggleSider(isClosed: boolean): void {
    this.isSiderClosed = isClosed;
  }
}
