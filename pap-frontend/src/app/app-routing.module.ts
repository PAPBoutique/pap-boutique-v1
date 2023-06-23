import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageNotFoundComponent } from './shared-components/page-not-found/page-not-found.component';

const routes: Routes = [
  {
    path :'' ,
    loadChildren : () => import ('./user/user.module').then(m=>m.UserModule)
  }
  ,{ 
    path: 'products',
    loadChildren: () => import('./products/products.module').then(m => m.ProductsModule)
  },
  {
    path : 'orders',
    loadChildren : () => import('./orders/orders.module').then(m=>m.OrdersModule)
  },
  { 
    path:'' , 
    redirectTo:"user" , 
    pathMatch:'full',
  },
  { 
    path:'**' , 
    component:  PageNotFoundComponent
  }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
