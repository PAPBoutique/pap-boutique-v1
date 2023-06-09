import { Injectable } from "@angular/core";
import { User } from "src/app/models/user/user";


const AUTH_TOKEN = "token";
const AUTH_USER = "user";
const AUTH_ROLE = "role";

@Injectable({
    providedIn: 'root'
})

export class AuthService {
    constructor() {}

    public saveToken(token: string) {
        window.sessionStorage.removeItem(AUTH_TOKEN);
        window.sessionStorage.setItem(AUTH_TOKEN, token);
    }

    public saveUser(user: User) {
        window.sessionStorage.removeItem(AUTH_USER);
        window.sessionStorage.removeItem(AUTH_ROLE);
        window.sessionStorage.setItem(AUTH_USER, JSON.stringify(user));
        window.sessionStorage.setItem(AUTH_ROLE,user.role.toString());
    }

    public getUser() {
        let user =window.sessionStorage.getItem(AUTH_USER);
        if(user) return JSON.parse(user);
    }

    public getToken() {
        return window.sessionStorage.getItem(AUTH_TOKEN);
    }

    public isLoggedIn() {
        const token = window.sessionStorage.getItem(AUTH_TOKEN);
        if(token!=null){
            const payload = atob(token.split('.')[1]);
            const parsedPayload = JSON.parse(payload); 
            return parsedPayload.exp > Date.now() / 1000; 
        } 
        return false ;
    }
    public getRole(){
        return window.sessionStorage.getItem(AUTH_ROLE);
    }

    public clearSession(){
        window.sessionStorage.clear();
    }

    public logout(){
        this.clearSession();
        return true ;
    }
}