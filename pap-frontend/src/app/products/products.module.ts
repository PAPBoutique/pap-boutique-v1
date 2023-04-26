import { NgModule } from '@angular/core';
import { ProductsRoutingModule } from './products-routing.module';
import { CreateProductComponent } from './page/create-product/create-product.component';
import { ListProductsComponent } from './page/list-products/list-products.component';
import { SharedComponentsModule } from '../shared-components/shared-components.module';
import { ConfirmationService, MessageService } from "primeng/api";
import { CommonModule } from '@angular/common';




@NgModule({
  declarations: [
    CreateProductComponent,
    ListProductsComponent
  ],
  imports: [
    ProductsRoutingModule, 
    SharedComponentsModule,
    CommonModule
  ], 
  
  providers: [ConfirmationService, MessageService],

})
export class ProductsModule {

 }
