import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';

import { User } from 'src/app/models/user/user';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent {
  constructor(
    private messageService: MessageService,
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router
  ) {}

  @Input() visible?: boolean;
  @Output() closeDialog = new EventEmitter<any>();
  loading: boolean = false;

  userForm = this.fb.group({
    username: ['', Validators.required],
    email: ['', Validators.required],
    address: ['', Validators.required],
    phoneNum: ['', Validators.required],
    role: ['', Validators.required],
    password: ['', Validators.required]
  });

  users: User[] =[ {
    username: "",
    email: "",
    address: "",
    phoneNum: 1234567890,
    password: "",
    role: ""
  }];

  onHide(e: any) {
    this.closeDialog.emit();
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
        }, 3000);
      },
      error => {
        console.log(error);
        this.showErrorMessage();
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
