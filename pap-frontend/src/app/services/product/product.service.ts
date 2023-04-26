import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';

import { Observable } from 'rxjs'
import { Product } from 'src/app/models/product/product.model';

const baseUrl = '/api/v1/products';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http:HttpClient) { }

  createProduct(products: Product[]): Observable<Product> {
    return this.http.post<Product>(baseUrl, products);
}

}
