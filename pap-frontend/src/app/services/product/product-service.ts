import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { Product } from '../../models/product/product';
import { PageContent } from 'src/app/shared-components/service/pageContent';



const baseUrl = '/api/v1/products';
const url = "/api/v1/products/productsWithPicture"
const cartAPI = "/api/v1/cart"
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }


  public addProductWithPicture(product : FormData)
  {
    return this.http.post<Product>(url, product)
  }

  public getTotalProducts(): Observable<number> {
    return this.http.get<number>(baseUrl + '/count');
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

    getAllProducts(): Observable<Product[]> {
      return this.http.get<Product[]>(baseUrl + "/allProducts");
    }

    public addToCart(productId?: number)
    {
      return this.http.get(cartAPI + "/addToCart/" + productId )
    }
    public getCartDetails()
    {
      return this.http.get(cartAPI + "/getCartDetails")
    }


    public getCartDetails1() {
      return this.http.get("http://localhost:9090/getCartDetails");
    }
        

    deleteCartItem(cartId: number): Observable<void> {
      const url = `${cartAPI}/deleteCartItem/${cartId}`;
      return this.http.delete<void>(url);
    }
}
