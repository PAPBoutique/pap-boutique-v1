import { HttpErrorResponse } from '@angular/common/http';
import { Component, SecurityContext } from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { map } from 'rxjs';
import { Product } from 'src/app/models/product/product';
import { AuthService } from 'src/app/services/auth/authService';
import { ImageProcessingService } from 'src/app/services/images/image-processing.service';
import { ProductService } from 'src/app/services/product/product-service';

@Component({
  selector: 'app-product-page',
  templateUrl: './product-page.component.html',
  styleUrls: ['./product-page.component.css']
})
export class ProductPageComponent {
 
  constructor(private productService : ProductService,     
    private route: ActivatedRoute,
    private imageProcessingService: ImageProcessingService,
    private sanitizer : DomSanitizer,
    private authService: AuthService,
    private messageService : MessageService
    ) {}


   ngOnInit() {
    this.route.params.subscribe(params => {
      const productId = +params['id'];
      this.fetchProduct(productId);
      
    });
  }
   
  product!: Product ;
  mainImage!: string;
  imageList: string[] = [];

  fetchProduct(id: number): void {
    this.productService.getProductById(id).pipe(
      map((product: Product) => this.imageProcessingService.createImages(product))
    ).subscribe(
      (product: Product) => {
        this.product = product;
        this.mainImage = this.product.productImages[0]?.url.toString();
      },
      (error: HttpErrorResponse) => {
        console.error(error);
      }
    );
  }
 
 
 
  changeImage(image: SafeUrl) {
    const mainImageElement = document.getElementById('product-main-image') as HTMLImageElement;
    const mainImageSrc = mainImageElement.src;
  
    const sanitizedSrc = this.sanitizer.sanitize(SecurityContext.URL, image);
    if (sanitizedSrc) {
      mainImageElement.setAttribute('src', sanitizedSrc);
    }
  
    const updatedImageList = this.imageList.map((img) => (img === image.toString() ? mainImageSrc : img));
    this.imageList = updatedImageList;
  }

  isLoggedIn!: boolean;

  addToCart() {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      const productId = +idParam;
      this.productService.addToCart(productId).subscribe(
      (response) => {
        this.isLoggedIn = this.authService.isLoggedIn();
        
        if (this.isLoggedIn) {
          this.showSuccessMessage();
        } else {
          this.showAddingToCartError();
        }
        
        setTimeout(() => {
          this.messageService.clear();
        }, 3000); 
        
        console.log(productId);
      },
      (error) => {
        console.log(error);
      }
    );} }

  showSuccessMessage() {
    this.messageService.add({ severity: 'success', summary: 'Product Added!', detail: 'Your product has been added to your cart.' });
  }
  
  showAddingToCartError() {
    this.messageService.add({
      severity: 'error',
      summary: 'Error occurred',
      detail: 'Please log in before attempting to add items to your cart.'
    });
  }

}
