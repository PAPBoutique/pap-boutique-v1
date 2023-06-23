import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OrdersListComponent } from './page/orders-list/orders-list.component';
import { AuthGuard } from '../services/auth/AuthGard';
import { PageNotFoundComponent } from '../shared-components/page-not-found/page-not-found.component';

const routes: Routes = [
  {path:'list' , component:OrdersListComponent , canActivate:[AuthGuard],data :{ role : 'ADMIN'}},
  {path :'' , redirectTo:'list' , pathMatch:'full'},
  { path:'**' , component:  PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrdersRoutingModule { }
