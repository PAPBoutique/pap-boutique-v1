import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/app/models/user/user';
import { PageContent } from 'src/app/shared-components/service/pageContent';
import { Observable } from 'rxjs';
import { Role } from 'src/app/models/user/roles';


const baseUrl = '/api/v1/users';
const rolesUrl = '/api/v1/roles';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getUsersByPage(page?: number, size?: number, filterValue: String = ""): Observable<PageContent<User>> {
    if (filterValue == null) filterValue = "";
    return this.http
      .get<PageContent<User>>(baseUrl + "?page=" + page + "&size=" + size + "&filterValue=" + filterValue);
  }

  deleteUser(id: number): Observable<Object> {
    return this.http.delete(baseUrl + "/" + id);
  }
  createUser(users: User[]): Observable<User> {
    return this.http.post<User>(baseUrl, users);
  }

  getAllRoles(): Observable<Role[]> {
    return this.http.get<Role[]>(rolesUrl);
  }


  putUser(user: User): Observable<User> {
    const url = `${baseUrl}/update/${user.id}`;
    return this.http.put<User>(url, user);
  }

  login(user: any): Observable<any> {
    return this.http.post(baseUrl + "/login", {
      username: user.username.value,
      password: user.password.value
    })
  }

  signup(users: User): Observable<User> {
    return this.http.post<User>(baseUrl + "/signup", users);
  }

}
