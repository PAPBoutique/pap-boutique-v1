import { NgModule } from '@angular/core';


import { ProductsRoutingModule } from './products-routing.module';
import { CreateProductComponent } from './page/create-product/create-product.component';
import { ListProductsComponent } from './page/list-products/list-products.component';
import { SharedComponentsModule } from '../shared-components/shared-components.module';
import { ProductTableComponent } from './components/product-table/product-table.component';
import { ProductService } from '../services/product/product-service';
import { ProductImagesComponent } from './components/product-images/product-images.component';
import { EditProductComponent } from './edit-product/edit-product/edit-product.component';
import { CommonModule } from '@angular/common';



@NgModule({
  declarations: [
    CreateProductComponent,
    ListProductsComponent,
    ProductTableComponent,
    ProductImagesComponent,
    EditProductComponent
  ],
  imports: [
    CommonModule,
    ProductsRoutingModule,
    SharedComponentsModule
  ],
  providers: [ProductService]
})
export class ProductsModule { }
