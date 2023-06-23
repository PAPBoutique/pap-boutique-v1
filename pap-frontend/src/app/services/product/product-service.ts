import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { Product } from '../../models/product/product';
import { PageContent } from 'src/app/shared-components/service/pageContent';



const baseUrl = '/api/v1/products';

const url = "/api/v1/products/productsWithPicture"
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }


  public addProductWithPicture(product : FormData)
  {
    return this.http.post<Product>(url, product)
  }

  createProduct(products: Product[]): Observable<Product> {
    return this.http.post<Product>(baseUrl, products);
}
  deleteProduct(id:number):Observable<Object>{
    return this.http.delete(baseUrl+"/"+id);
  }

  getProductsByPage(page ?: number , size ?:number , filterValue:String = ""):Observable<PageContent<Product>>{
    if(filterValue==null) filterValue="";
    return this.http
    .get<PageContent<Product>>(baseUrl+"?page="+page+"&size="+size+"&filterValue="+filterValue);
  }

    updateProduct(product: Product): Observable<Product> {
      const url = `${baseUrl}/update/${product.id}`;
      return this.http.put<Product>(url, product);
    }
    
    getProductById(id: number): Observable<Product> {
      const url = `${baseUrl}/${id}`;
      return this.http.get<Product>(url);
    }
}
