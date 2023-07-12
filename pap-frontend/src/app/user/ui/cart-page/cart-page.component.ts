import { Component } from '@angular/core';
import { forkJoin } from 'rxjs';
import { ImageProcessingService } from 'src/app/services/images/image-processing.service';
import { ProductService } from 'src/app/services/product/product-service';
declare function greet(): void;

@Component({
  selector: 'app-cart-page',
  templateUrl: './cart-page.component.html',
  styleUrls: ['./cart-page.component.css']
})
export class CartPageComponent {

  constructor(private productService : ProductService, private imageProcessingService : ImageProcessingService) {}

  ngOnInit() {
      this.getCartDetails();
  }

  cartDetails: any[] = [];


  getCartDetails() {
    this.productService.getCartDetails().subscribe(
      (response: any[] | Object) => {
        if (Array.isArray(response)) {
          console.log(response);
          this.cartDetails = response;
          this.calculateTotalAmount();

          const imageRequests = this.cartDetails.map((cartItem) => {
            return this.imageProcessingService.createImages(cartItem.product);
          });
  
          forkJoin(imageRequests).subscribe(
            (productsWithImages: any[]) => {
              this.cartDetails.forEach((cartItem, index) => {
                cartItem.product = productsWithImages[index];
              });
            },
            (error) => {
              console.log(error);
            }
          );
        } 
      },
      (error) => {
        console.log(error);
      }
    );
  }

  totalAmount: number = 0;
  calculateTotalAmount() {
    this.totalAmount = 0;
    for (let cartItem of this.cartDetails) {
      this.totalAmount += cartItem.product.price * cartItem.quantity;
    }
  }
  deleteCartItem(cartId: number): void {
    this.productService.deleteCartItem(cartId).subscribe(
      () => {
        const updatedCartList = this.cartDetails.filter(item => item.cartId !== cartId);
        this.cartDetails = updatedCartList;
        window.location.reload()
      },
      (error) => {
        console.error('Failed to delete item:', error);
      }
    );
  }

}
