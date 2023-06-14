import { Component, ElementRef, Renderer2, ViewChild } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Button } from 'primeng/button';
import { Password } from 'primeng/password';
import { Role } from 'src/app/models/user/roles';
import { User } from 'src/app/models/user/user';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  @ViewChild('passwordInput') passwordInput!: Password;

  constructor(
    private messageService: MessageService,
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router,
    private renderer : Renderer2
  ) { }
  ngAfterViewInit() {
    const inputElement = this.passwordInput.el.nativeElement.querySelector('input');
    inputElement.id = 'password';
  }
  userForm = this.fb.group({
    username: ['', Validators.required],
    email: ['', Validators.email],
    address: ['', Validators.required],
    phoneNum: ['', [Validators.required, Validators.minLength(9), Validators.maxLength(9), this.firstCharValidator]],
    password: ['', Validators.required],
    role: ['', Validators.nullValidator]
  });


  firstCharValidator(control: AbstractControl) {
    const firstChar = control.value?.toString().charAt(0);
    if (firstChar !== '6' && firstChar !== '7') {
      return { firstCharInvalid: true };
    }
    return null;
  }


  ngOnInit(): void {
    this.users = {
      username: "",
      email: "",
      address: "",
      password: "",
      phoneNum : 6,
      role: Role.T
    };
    this.roles = [
      { name: 'CLIENT', value: Role.T },
    ];
    console.log(this.roles)
  }

  roles: { name: string; value: Role; }[] = [];

  users!: User

  signupUser() {
    if (this.userForm.valid) {
      this.userService.signup(this.users).subscribe(
        response => {
          console.log(response);
          this.showSuccessMessage();
          setTimeout(() => {
            this.router.navigate(['/login'])
          }, 2000);
        },
        error => {
          console.log(error);
          this.showErrorMessage();
        }
      );
    }
  }

  markFormControlsAsTouched() {
    Object.values(this.userForm.controls).forEach(control => {
      control.markAsTouched();
    });
  }

  showErrorMessage() {
    this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Sorry, user creation failed. Try again later.' });
  }

  showSuccessMessage() {
    this.messageService.add({ severity: 'success', summary: 'Congratulations!', detail: 'Your account has been created.' });
  }

  closeToast() {
    this.router.navigate(['/login']);
  }
}
