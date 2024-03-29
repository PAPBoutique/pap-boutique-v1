import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateProductComponent } from './page/create-product/create-product.component';
import { ListProductsComponent } from './page/list-products/list-products.component';
import { PageNotFoundComponent } from '../shared-components/page-not-found/page-not-found.component';

const routes: Routes = [
  {path:'create' , component: CreateProductComponent},
  {path :'list' , component: ListProductsComponent},
  {path :'' , redirectTo:'list' , pathMatch:'full'},
  { path:'**' , component:  PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductsRoutingModule {}