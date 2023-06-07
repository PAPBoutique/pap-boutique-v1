import { Role } from "./roles";

export type User = {
    id?: number; 
    username: string; 
    email: string; 
    address: string;
    phoneNum?: number; 
    role: Role;
    password : string 
}