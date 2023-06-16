import { FileHandle } from "./file-handle.model";

export type Product = {
    id?: number; 
    name: string; 
    description: string; 
    quantity: number;
    price: number; 
    productImages: FileHandle[];
}