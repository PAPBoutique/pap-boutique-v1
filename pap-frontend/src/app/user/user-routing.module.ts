import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './admin/page/login/login.component';
import { SignupComponent } from './admin/page/signup/signup.component';
import { UsersListComponent } from './admin/page/users-list/users-list.component';
import { EditUserComponent } from './admin/page/edit-user/edit-user.component';
import { AuthGuard } from '../services/auth/AuthGard';

const routes: Routes = [
  {path:'login' , component : LoginComponent , canActivate:[AuthGuard]},
  {path:'' , redirectTo:'login' , pathMatch:'full'},
  {path:'users', component : UsersListComponent , canActivate:[AuthGuard] , data :{ role : 'ADMIN'}},
  {path:'signup' , component : SignupComponent , canActivate:[AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
