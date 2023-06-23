import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from 'src/app/models/order/order';
import { Product } from 'src/app/models/product/product';
import { PageContent } from 'src/app/shared-components/service/pageContent';


const baseUrl = '/api/v1/orders';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  constructor(private http: HttpClient) { }

  getOrdersByPage(page?: number, size?: number, filterValue: String = ""): Observable<PageContent<Order>> {
    if (filterValue == null) filterValue = "";
    return this.http
      .get<PageContent<Order>>(baseUrl + "?page=" + page + "&size=" + size + "&filterValue=" + filterValue);
  }

  updateOrder(order: Order): Observable<Order> {
    const url = `${baseUrl}/update/${order.id}`;
    return this.http.put<Order>(url, order);
  }
  createOrder(orders: Order[]): Observable<Order> {
    return this.http.post<Order>(baseUrl, orders);
  }
  checkOrders(order : Order) : Observable<Object>{
    console.log("orderid : "+order.id);
    return this.http.post(baseUrl+"/check/"+order.id,{});
  }

  getProductOrder(order : Order)  {
    this.http.get<Product>(baseUrl+"/product/"+order.id).subscribe((data)=>{
      order.product=data.name ;
    })
  }

  test() : void{
    console.log("say hi to sherly");
  }
}
