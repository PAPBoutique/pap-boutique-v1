import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { Role } from 'src/app/models/user/roles';
import { User } from 'src/app/models/user/user';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent {
  userForm = this.fb.group({
    username: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    address: ['', Validators.required],
    phoneNum: ['', [Validators.required, Validators.minLength(9), Validators.maxLength(9), this.firstCharValidator]],
    role: ['', Validators.required]
  });

  firstCharValidator(control: AbstractControl) {
    const firstChar = control.value?.toString().charAt(0);
    if (firstChar !== '6' && firstChar !== '7') {
      return { firstCharInvalid: true };
    }
    return null;
  }

  loading: boolean = false;
  @Input() visibleEdit: boolean = true;
  @Output() closeDialog = new EventEmitter<any>();
  @Input() selectedUser!: User;
  @Input() visible: boolean = false;

  onHide(e:any){
    this.closeDialog.emit();
  }

  constructor(
    private messageService: MessageService,
    private fb: FormBuilder,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.getAllRoles();
  }

  updateUser(user: User) {
    if (this.userForm.valid) {
      this.userService.putUser(user).subscribe(
        (data: User) => {
          console.log(data);
         
          this.showsuccessMessage();
          this.loading = true;
          console.log(this.loading);
          setTimeout(() => {
            window.location.reload();
          }, 2000);
        },
        (error: any) => {
          console.log(error);
          this.showErrorMessage();
        }
      );
    } 
    
  }

  showsuccessMessage() {
    this.messageService.add({
      severity: 'success',
      summary: 'Your user has been updated!',
      detail: this.selectedUser.username + ' has been updated'
    });
  }

  showErrorMessage() {
    this.messageService.add({
      severity: 'error',
      summary: 'Error occurred',
      detail: 'There was an error while updating the user.'
    });
  }

  closeToast() {
    window.location.reload();
  }

  roles!: Role[];

  getAllRoles() {
    this.userService.getAllRoles().subscribe(
      (response: Role[]) => {
        console.log(response);
        this.roles = response.map((role) => role);
      },
      (error) => {
        console.error('Error fetching roles:', error);
      }
    );
  }
}
