import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductsRoutingModule } from './products-routing.module';
import { CreateProductComponent } from './page/create-product/create-product.component';
import { ListProductsComponent } from './page/list-products/list-products.component';



@NgModule({
  declarations: [
    CreateProductComponent,
    ListProductsComponent
  ],
  imports: [
    CommonModule,
    ProductsRoutingModule
  ]
})
export class ProductsModule { }
