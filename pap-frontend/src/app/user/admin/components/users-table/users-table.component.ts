import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { ConfirmEventType, ConfirmationService, LazyLoadEvent, MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { Observable } from 'rxjs';
import { Role } from 'src/app/models/user/roles';
import { User } from 'src/app/models/user/user';

@Component({
  selector: 'app-users-table',
  templateUrl: './users-table.component.html',
  styleUrls: ['./users-table.component.css']
})
export class UsersTableComponent {
  visibleEdit: boolean = false;
  @Input() selectedUser: User = {
    username: '',
    email: '',
    address: '',
    password: '',
    role: Role.T
  };
  
  @Input() deleteOne!: (id: any) => Observable<Object>;
  @Output() pageChange = new EventEmitter<{ page: number; size: number; filterValue: string }>();
  @ViewChild('dt') dt?: Table;
  @Input() users: User[] = [];

  

  @Input() tableSize: number = this.users.length;
  clonedUsers: { [s: string]: User } = {};
  filteredRows: User[] = [];
  visibleAdd: boolean = false;

  constructor(
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) {}

  loadUsers(event: LazyLoadEvent) {
    if (event.rows && event.first?.toString) {
      this.pageChange.emit({ page: event.first / event.rows, size: event.rows, filterValue: event.globalFilter });
    }
  }

  confirmDelete(user: User) {
    this.confirmationService.confirm({
      message: 'Are you sure that you want to delete this user?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.deleteOne(user.id).subscribe(() => this.dt?.clear());
        this.messageService.add({
          severity: 'info',
          summary: 'Confirmed',
          detail: 'You have deleted the user'
        });
      },
      reject: (type: any) => {
        switch (type) {
          case ConfirmEventType.REJECT:
            this.messageService.add({ severity: 'error', summary: 'Rejected', detail: 'You have rejected' });
            break;
          case ConfirmEventType.CANCEL:
            this.messageService.add({ severity: 'warn', summary: 'Cancelled', detail: 'You have cancelled' });
            break;
        }
      }
    });
  }

  showAddDialog() {
    this.visibleAdd = true;
  }

  closeAddDialog() {
    this.visibleAdd = false;
  }

  showDialog(user1: User) {
    this.selectedUser = { ...user1 };
    this.visibleEdit = true;
  }

  closeEditDialog() {
    this.visibleEdit = false;
  }
}