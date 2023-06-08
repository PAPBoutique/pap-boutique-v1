import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot } from "@angular/router";
import { AuthService } from "./authService";

@Injectable({
  providedIn: 'root'
})

export class AuthGuard {

  constructor(private authService: AuthService, private router: Router) { }

  public canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {

    const url = state.url;
    return this.checkUserLogin(next, url);

  }
  checkUserLogin(route: ActivatedRouteSnapshot, url: any): boolean {
    if (this.authService.isLoggedIn()) {
      const userRole = this.authService.getRole();
      console.log(typeof this.authService.getUser());
      if(route.routeConfig?.path=="signup" || route.routeConfig?.path =='login'){
        this.router.navigate(['/products']);
        return false ;
      }
      if (route.data["role"] != userRole) {
        this.router.navigate(['/home']);
        return false;
      }
      return true;
    } 
    
    else {
      if(route.routeConfig?.path=="signup" || route.routeConfig?.path =='login'){
        return true ;
      }
    }
    this.router.navigate(['/login']);
    return false;
  }


}