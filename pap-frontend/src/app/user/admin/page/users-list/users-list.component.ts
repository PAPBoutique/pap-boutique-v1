import { Component } from '@angular/core';
import { User } from 'src/app/models/user/user';
import { PageContent } from 'src/app/shared-components/service/pageContent';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent {
  users : User[] = [];
  totalUsers : number = 0 ;

  constructor(private userService: UserService) { }
  deleteUser = (id: number) => {
    return this.userService.deleteUser(id);
  }

  fetchProducts(page: number = 0, size: number = 5, filterValue: String = "") {
    this.userService.getUsersByPage(page, size, filterValue).subscribe((usersPage: PageContent<User>) => {
      if (usersPage && usersPage.content) {
        this.users = usersPage.content;
        this.totalUsers = usersPage.totalElements;
      }
    });
  }
  onTableChange(event: { page: number, size: number, filterValue: String }) {
    this.fetchProducts(event.page, event.size, event.filterValue);
  }

}
