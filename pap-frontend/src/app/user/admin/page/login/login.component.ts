import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import {  Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { AuthService } from 'src/app/services/auth/authService';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(
    private formBuilder : FormBuilder,
    private userService : UserService,
    private authService : AuthService,
    private router : Router,
    private messageService : MessageService
  ){}

  loginForm = this.formBuilder.group({
    username : [''],
    password : ['']
  });

  get formControls() { return this.loginForm.controls}

  login(){
    this.userService.login(this.formControls).subscribe((data)=>{
      console.log(data);
      this.authService.saveToken(data.token);
      this.authService.saveUser(data.user);
      setTimeout(()=> {
        this.router.navigate(['/products'])
      },3000);
    },error=>{
      console.log("login error");
      this.loginError();
    })
  }

  loginError(){
    this.messageService.add({ severity: 'error', summary: 'Error occurred', detail: 'Username or password incorrect. Please try again' });
  }
}
