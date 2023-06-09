import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from './authService';

@Injectable({
    providedIn: 'root'
  })
  export class ApiInterceptorService implements HttpInterceptor {
    
    constructor(private authService : AuthService) { }
  
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      const token = this.authService.getToken();
      if (!token) {
        return next.handle(request);
      }
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer `+token
        }
      });
      if(!this.authService.isLoggedIn()){
        this.authService.clearSession();
      }
      return next.handle(request);
    }
  }