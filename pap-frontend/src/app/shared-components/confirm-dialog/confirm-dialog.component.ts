import { Component, Input, ViewChild } from '@angular/core';
import { ConfirmDialog } from 'primeng/confirmdialog';

@Component({
  selector: 'app-confirm-dialog',
  templateUrl: './confirm-dialog.component.html',
  styleUrls: ['./confirm-dialog.component.css']
})
export class ConfirmDialogComponent {
  @ViewChild('confirmDialog') confirmDialog?: ConfirmDialog;
  @Input() btnYes ?: string ;
  @Input() btnNo ?: string  ;
  @Input() btnClose ?: string ;
 
}
