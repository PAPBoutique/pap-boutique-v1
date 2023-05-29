import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { Product } from '../../models/product/product';
import { PageContent } from 'src/app/shared-components/service/pageContent';



const baseUrl = '/api/v1/products';
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  createProduct(products: Product[]): Observable<Product> {
    return this.http.post<Product>(baseUrl, products);
}
  putProduct(id:number , body:Product): Observable<Product>{
    return this.http.put<Product>(baseUrl+id,body);
  }

  deleteProduct(id:number):Observable<Object>{
    return this.http.delete(baseUrl+"/"+id);
  }

  getProductsByPage(page ?: number , size ?:number , filterValue:String = ""):Observable<PageContent<Product>>{
    if(filterValue==null) filterValue="";
    return this.http
    .get<PageContent<Product>>(baseUrl+"?page="+page+"&size="+size+"&filterValue="+filterValue);  }
}
