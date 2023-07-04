import { HttpErrorResponse } from '@angular/common/http';
import { Component, SecurityContext } from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { map } from 'rxjs';
import { Product } from 'src/app/models/product/product';
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
    private sanitizer : DomSanitizer
    ) {}

  ngOnInit(): void {
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

        console.log('Single Product Data:', this.product);
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
  

}
