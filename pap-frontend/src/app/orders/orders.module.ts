import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OrdersRoutingModule } from './orders-routing.module';
import { OrdersListComponent } from './page/orders-list/orders-list.component';
import { OrdersTableComponent } from './component/orders-table/orders-table.component';
import { SharedComponentsModule } from '../shared-components/shared-components.module';
import { OrdersService } from '../services/orders/orders.service';


@NgModule({
  declarations: [
    OrdersListComponent,
    OrdersTableComponent
  ],
  imports: [
    CommonModule,
    OrdersRoutingModule,
    SharedComponentsModule
  ],
  providers :[OrdersService]
})
export class OrdersModule { }
