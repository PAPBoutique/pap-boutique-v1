import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './admin/page/login/login.component';
import { SignupComponent } from './admin/page/signup/signup.component';
import { UsersListComponent } from './admin/page/users-list/users-list.component';
import { EditUserComponent } from './admin/page/edit-user/edit-user.component';

const routes: Routes = [
  {path:'login' , component : LoginComponent},
  {path:'' , redirectTo:'login' , pathMatch:'full'},
  {path:'users', component : UsersListComponent},
  {path:'signup' , component : SignupComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
