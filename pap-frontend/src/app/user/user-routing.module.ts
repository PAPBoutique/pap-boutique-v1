import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './admin/page/login/login.component';
import { SignupComponent } from './admin/page/signup/signup.component';
import { UsersListComponent } from './admin/page/users-list/users-list.component';
import { AuthGuard } from '../services/auth/AuthGard';
import { DashboardComponent } from './admin/page/dashboard/dashboard.component';
import { HomepageComponent } from './ui/homepage/homepage.component';
import { ProductPageComponent } from './ui/product-page/product-page.component';
import { CartPageComponent } from './ui/cart-page/cart-page.component';

const routes: Routes = [
  {path:'login' , component : LoginComponent , canActivate:[AuthGuard]},
  {path:'' , redirectTo:'home' , pathMatch:'full'},
  {path:'users', component : UsersListComponent , canActivate:[AuthGuard] , data :{ role : 'ADMIN'}},
  {path:'signup' , component : SignupComponent , canActivate:[AuthGuard]},
  {path : 'dashboard', component : DashboardComponent, canActivate:[AuthGuard] , data :{ role : 'ADMIN'}},
  {path: 'home', component : HomepageComponent},
  {path : 'product/:id' , component : ProductPageComponent},
  {path: 'cart', component : CartPageComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule {}
