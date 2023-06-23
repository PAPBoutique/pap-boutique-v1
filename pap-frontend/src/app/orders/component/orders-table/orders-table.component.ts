import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { ConfirmEventType, ConfirmationService, LazyLoadEvent, MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { Observable } from 'rxjs';
import { Order } from 'src/app/models/order/order';

@Component({
  selector: 'app-orders-table',
  templateUrl: './orders-table.component.html',
  styleUrls: ['./orders-table.component.css']
})
export class OrdersTableComponent {
  visible: boolean = false;
  visibleAdd: boolean = false;
  @Input() checkSelected!: (selectedOrders: any) => void;
  @Input() selectedOrder: Order = {
    product : '' ,
    quantity : 0 ,
    client :'',
    creationDate : '',
    price : 0,
    status : '',
    checked:false
  };

  @Output() pageChange = new EventEmitter<{ page: number; size: number; filterValue: string }>();
  @ViewChild('dt') dt?: Table;
  @Input() orders: Order[] = [];
  @Input() tableSize: number = this.orders.length;
  clonedOrders: { [s: string]: Order } = {};
  filteredRows: Order[] = [];
  selectedOrders ?: Order ;

  constructor(
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) {}

  ngAfterViewInit(){
    const firstBtn = this.dt?.el.nativeElement.querySelector(".p-paginator-first");
    const lastBtn = this.dt?.el.nativeElement.querySelector(".p-paginator-last");
    const prevBtn = this.dt?.el.nativeElement.querySelector(".p-paginator-prev");
    const nextBtn = this.dt?.el.nativeElement.querySelector(".p-paginator-next");
    const dropdown = this.dt?.el.nativeElement.querySelector("p-dropdown");
    firstBtn.id = "order_paginator_first";
    lastBtn.id = "order_paginator_last";
    prevBtn.id ="order_paginator_prev";
    nextBtn.id ="order_paginator_next";
    dropdown.id = "order_paginator_dropdown";
  }

  updateAddDialog(){
    this.visibleAdd = false ;
  }
  updateEditDialog(){
    this.visible = false ;
  }


  loadOrders(event: LazyLoadEvent) {
    if (event.rows && event.first?.toString) {
      this.pageChange.emit({ page: event.first / event.rows, size: event.rows, filterValue: event.globalFilter });
    }
  }

  showDialog(order: Order) {
    this.selectedOrder = order;
    this.visible = true;
  }



  checkOrder(){
    if(this.isSelectionEmpty()){
      this.messageService.add({ severity: 'error', summary: 'Rejected', detail: 'No order selected' });
      return ;
    }
    this.confirmationService.confirm({
      message: 'Are you sure that you want to check this orders?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.checkSelected(this.selectedOrders);
        window.location.reload();
        this.selectedOrders = this.selectedOrder ;
        this.messageService.add({
          severity: 'info',
          summary: 'Confirmed',
          detail: 'Orders selected checked'
        });
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

  isChecked(order : Order) : boolean {
    return order.checked ;
  }

  isSelectionEmpty(){
    return this.selectedOrders==null;
  }
}
