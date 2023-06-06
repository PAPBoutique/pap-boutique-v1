import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { LoginComponent } from './admin/page/login/login.component';
import { SignupComponent } from './admin/page/signup/signup.component';
import { UsersListComponent } from './admin/page/users-list/users-list.component';
import { SharedComponentsModule } from '../shared-components/shared-components.module';
import { CreateUserComponent } from './admin/page/create-user/create-user.component';
import { UsersTableComponent } from './admin/components/users-table/users-table.component';


@NgModule({
  declarations: [
    LoginComponent,
    SignupComponent,
    UsersListComponent,
    CreateUserComponent,
    UsersTableComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    SharedComponentsModule
  ]
})
export class UserModule { }
