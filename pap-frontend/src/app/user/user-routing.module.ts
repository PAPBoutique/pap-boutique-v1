import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './admin/page/login/login.component';
import { SignupComponent } from './admin/page/signup/signup.component';

const routes: Routes = [
  {path:'login' , component : LoginComponent},
  {path:'' , redirectTo:'login' , pathMatch:'full'},
  {path:'signup' , component : SignupComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
