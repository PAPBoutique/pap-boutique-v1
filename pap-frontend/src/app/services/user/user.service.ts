import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/app/models/user/user';
import { PageContent } from 'src/app/shared-components/service/pageContent';
import { Observable } from 'rxjs';


const baseUrl = '/api/v1/users';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http : HttpClient) { }

  getUsersByPage(page ?: number , size ?:number , filterValue:String = ""):Observable<PageContent<User>>{
    if(filterValue==null) filterValue="";
    return this.http
    .get<PageContent<User>>(baseUrl+"?page="+page+"&size="+size+"&filterValue="+filterValue);
  }

  deleteUser(id:number):Observable<Object>{
    return this.http.delete(baseUrl+"/"+id);
  }
  createUser(users: User[]): Observable<User> {
    return this.http.post<User>(baseUrl, users);
}
}
