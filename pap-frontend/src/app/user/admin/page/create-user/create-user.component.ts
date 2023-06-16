import { Component, ElementRef, EventEmitter, Input, Output, Renderer2, ViewChild } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Dropdown } from 'primeng/dropdown';
import { Password } from 'primeng/password';
import { Role } from 'src/app/models/user/roles';

import { User } from 'src/app/models/user/user';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent {

  ngOnInit() {
    this.getAllRoles();
  }

  constructor(
    private messageService: MessageService,
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router,
    private renderer : Renderer2
  ) {}

  @Input() visible?: boolean;
  @Output() closeDialog = new EventEmitter<any>();
  @ViewChild('passwordInput') passwordInput!: Password;
  @ViewChild('dropdownInput') dropdownInput!: Dropdown;
  loading: boolean = false;

  ngAfterViewInit() {
    const inputElement = this.passwordInput.el.nativeElement.querySelector('input');
    const inputDropdown = this.dropdownInput.el.nativeElement.querySelector('input');
    inputElement.id = 'cuser-password';
    inputDropdown.id ='cuser-role'
  }

  userForm = this.fb.group({
    username: ['', Validators.required],
    email: ['', Validators.email],
    address: ['', Validators.required],
    phoneNum: ['', [Validators.required, Validators.minLength(9), Validators.maxLength(9), this.firstCharValidator]],
    password: ['', Validators.required],
    role: ['', Validators.nullValidator]
  });

  onHide(e: any) {
    this.closeDialog.emit();
  }
   
  users: User[] =[ {
    username: "",
    email: "",
    address: "",
    password: "",
  
    role: Role.T
  }];

  firstCharValidator(control: AbstractControl) {
    const firstChar = control.value?.toString().charAt(0);
    if (firstChar !== '6' && firstChar !== '7') {
      return { firstCharInvalid: true };
    }
    return null;
  }
  
  createUser() {
    if (this.userForm.invalid) {
      return;
    }
    this.userService.createUser(this.users).subscribe(
      response => {
        console.log(response);
        this.loading = true;
        this.showSuccessMessage();
        setTimeout(() => {
          console.log("after loading");
          window.location.reload();
        }, 2000);
      },
      error => {
        console.log(error);
        this.showErrorMessage();
      }
    );
  }
  roles!: Role[];

  getAllRoles() {
    this.userService.getAllRoles().subscribe(
      (response: Role[]) => {
        console.log(response);
        this.roles = response.map(role => role);
      },
      (error) => {
        console.error('Error fetching roles:', error);
      }
    );
  }

  showErrorMessage() {
    this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Sorry, user creation failed. Try again later.' });
  }

  showSuccessMessage() {
    this.messageService.add({ severity: 'success', summary: 'Congratulations!', detail: 'User has been created.' });
  }

  closeToast() {
    this.router.navigate(['/users/list']);
  }
}
