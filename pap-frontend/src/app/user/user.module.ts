import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { LoginComponent } from './admin/page/login/login.component';
import { SignupComponent } from './admin/page/signup/signup.component';
import { UsersListComponent } from './admin/page/users-list/users-list.component';
import { SharedComponentsModule } from '../shared-components/shared-components.module';
import { CreateUserComponent } from './admin/page/create-user/create-user.component';
import { UsersTableComponent } from './admin/components/users-table/users-table.component';
import { EditUserComponent } from './admin/page/edit-user/edit-user.component';
import { DashboardComponent } from './admin/page/dashboard/dashboard.component';
import { HomepageComponent } from './ui/homepage/homepage.component';
import { ProductPageComponent } from './ui/product-page/product-page.component';
import { CartPageComponent } from './ui/cart-page/cart-page.component';
import { BuyProductComponent } from './ui/buy-product/buy-product.component';




@NgModule({
  declarations: [
    LoginComponent,
    SignupComponent,
    UsersListComponent,
    CreateUserComponent,
    UsersTableComponent,
    EditUserComponent,
    HomepageComponent,
    DashboardComponent,
    ProductPageComponent,
    CartPageComponent,
    BuyProductComponent,
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    SharedComponentsModule
  ]
})
export class UserModule { }
