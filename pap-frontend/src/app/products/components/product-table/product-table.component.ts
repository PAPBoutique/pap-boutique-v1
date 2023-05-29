import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { Product } from '../../../models/product/product';
import { ConfirmEventType, ConfirmationService, LazyLoadEvent, MessageService } from 'primeng/api';
import { Table } from 'primeng/table'
import { Observable } from 'rxjs';

@Component({
  selector: 'app-product-table',
  templateUrl: './product-table.component.html',
  styleUrls: ['./product-table.component.css']
})
export class ProductTableComponent {
  @Input() deleteOne!: (id:any) => Observable<Object> ;
  @Output() pageChange = new EventEmitter<{ page: number, size: number , filterValue:String}>();
  @ViewChild('dt') dt?: Table;
  @Input() products : Product[]=[];
  @Input() tableSize : number= this.products.length ;
  clonedProducts: { [s: string]: Product } = {};
  filteredRows: Product[] = [];

  constructor(private messageService: MessageService,private confirmationService : ConfirmationService) { }

  loadCustomers(event: LazyLoadEvent){
    if(event.rows && event.first?.toString ){
      this.pageChange.emit({ page: event.first/event.rows, size: event.rows , filterValue:event.globalFilter });
    }
  }

  confirmDelete(product:Product){
    this.confirmationService.confirm({
      message: 'Are you sure that you want to delete this product?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.deleteOne(product.id).subscribe(()=>this.dt?.clear());
        this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: 'You have deleted the product' });
      },
      reject: (type: any) => {
          switch (type) {
              case ConfirmEventType.REJECT:
                  this.messageService.add({ severity: 'error', summary: 'Rejected', detail: 'You have rejected' });
                  break;
              case ConfirmEventType.CANCEL:
                  this.messageService.add({ severity: 'warn', summary: 'Cancelled', detail: 'You have cancelled' });
                  break;
          }
      }
  });
  }
  
}
