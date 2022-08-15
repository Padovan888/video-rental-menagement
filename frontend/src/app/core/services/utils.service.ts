import { Injectable } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';

@Injectable({
  providedIn: 'root',
})
export class UtilsService {
  constructor(private messageService: NzMessageService) {}

  showSuccessMessage(message?: string): void {
    this.messageService.success(message || 'Ação realizada com sucesso');
  }

  showErrorMessage(message?: string): void {
    this.messageService.error(message || 'Erro ao realizar a ação');
  }
}
