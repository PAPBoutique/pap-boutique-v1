import { Component } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Order } from 'src/app/models/order/order';
import { OrdersService } from 'src/app/services/orders/orders.service';
import { PageContent } from 'src/app/shared-components/service/pageContent';

@Component({
  selector: 'app-orders-list',
  templateUrl: './orders-list.component.html',
  styleUrls: ['./orders-list.component.css']
})
export class OrdersListComponent {
  constructor(private orderService: OrdersService,private messageService: MessageService) { }
  orders: Order[] = [];
  totalOrders: number = 0;
  
  fetchOrders(page: number = 0, size: number = 5, filterValue: String = "") {
    this.orderService.getOrdersByPage(page, size, filterValue).subscribe((ordersPage: PageContent<Order>) => {
      if (ordersPage && ordersPage.content) {
        this.orders = ordersPage.content;
        this.orders.forEach(o=> {
          this.orderService.getProductOrder(o);
          if(o.checked) o.status="Checked";
          else o.status="Not Checked yet";
        });
        this.totalOrders = ordersPage.totalElements;
      }
    });
  }
  onTableChange(event: { page: number, size: number, filterValue: String }) {
    this.fetchOrders(event.page, event.size, event.filterValue);
  }
  checkOrders = (orders : Order[]) => {
    orders.forEach((order)=>{
      this.orderService.checkOrders(order).subscribe((res)=>{
        this.fetchOrders;
        console.log(res.toString);
        this.messageService.add({
          severity: 'info',
          summary: 'Confirmed',
          detail: "order checked"
        });
      });
    })
  }
}
