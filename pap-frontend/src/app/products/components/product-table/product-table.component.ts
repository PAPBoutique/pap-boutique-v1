import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { Product } from '../../../models/product/product';
import { ConfirmEventType, ConfirmationService, LazyLoadEvent, MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-product-table',
  templateUrl: './product-table.component.html',
  styleUrls: ['./product-table.component.css']
})
export class ProductTableComponent {

  visible: boolean = false;
  visibleAdd: boolean = false;
  @Input() selectedProduct: Product = {
    name: '',
    description : '',
    quantity : 0,
    price : 0
  };

  @Input() deleteOne!: (id: any) => Observable<Object>;
  @Output() pageChange = new EventEmitter<{ page: number; size: number; filterValue: string }>();
  @ViewChild('dt') dt?: Table;
  @Input() products: Product[] = [];
  @Input() tableSize: number = this.products.length;
  clonedProducts: { [s: string]: Product } = {};
  filteredRows: Product[] = [];

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
    firstBtn.id = "product_paginator_first";
    lastBtn.id = "product_paginator_last";
    prevBtn.id ="product_paginator_prev";
    nextBtn.id ="product_paginator_next";
    dropdown.id = "product_paginator_dropdown";
  }

  updateAddDialog(){
    this.visibleAdd = false ;
  }
  updateEditDialog(){
    this.visible = false ;
  }


  loadProducts(event: LazyLoadEvent) {
    if (event.rows && event.first?.toString) {
      this.pageChange.emit({ page: event.first / event.rows, size: event.rows, filterValue: event.globalFilter });
    }
  }

  confirmDelete(product: Product) {
    this.confirmationService.confirm({
      message: 'Are you sure that you want to delete this product?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.deleteOne(product.id).subscribe(() => {
          let index = this.products.findIndex(p=>p.id===product.id);
          this.products.splice(index,1);
          this.dt?.clear();
          console.log(this.products);
        });
        this.messageService.add({
          severity: 'info',
          summary: 'Confirmed',
          detail: 'You have deleted the product'
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

  showDialog(product: Product) {
    this.selectedProduct = product;
    this.visible = true;
  }

  showAddDialog() {
    this.visibleAdd = true;
  }

}
