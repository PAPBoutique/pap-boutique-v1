import { HttpErrorResponse } from '@angular/common/http';
import { Component, ElementRef, Input} from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { map } from 'rxjs';
import { Product } from 'src/app/models/product/product';
import { AuthService } from 'src/app/services/auth/authService';
import { ImageProcessingService } from 'src/app/services/images/image-processing.service';
import { ProductService } from 'src/app/services/product/product-service';


@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent {
  isLoggedIn!: boolean;

  products : Product[] = []
  ngOnInit(): void {
    this.getAllProducts();
    console.log(this.isLoggedIn)
    
  }
 
  constructor(private productService : ProductService, private imageProcessingService : ImageProcessingService, 
              private sanitizer : DomSanitizer,
              private router : Router,
              private elementRef: ElementRef,
              private messageService: MessageService,
              private authService: AuthService
              ) {}

  getAllProducts() {
    this.productService.getAllProducts()
      .pipe(
        map((x: Product[], i) => x.map((product: Product) => this.imageProcessingService.createImages(product)))
      )
      .subscribe(
        (resp: Product[]) => {
          this.products = resp;
        },
        (error: HttpErrorResponse) => {
          console.log(error);
        }
      );
  }
  addToCart(productId?: number)
  {
    this.productService.addToCart(productId).subscribe(
      (response) =>
      {this.isLoggedIn = this.authService.isLoggedIn();
        if (this.isLoggedIn) {
          this.showSuccessMessage();
          setTimeout(() => {
            this.messageService.clear();
          }, 3000); 
        } else {
          this.showAddingToCartError();
          setTimeout(() => {
            this.messageService.clear();
          }, 3000); 
        }
      },
      (error) =>
      {
        console.log(error)
      }
    )
      console.log(productId)
  }


  getSafeImageUrl(url: string): SafeUrl {
    return this.sanitizer.bypassSecurityTrustUrl(url);
  }

  goToProductPage(productId?: number) {
    if (productId !== undefined) {
      const url = `/product/${productId}`;
      this.router.navigateByUrl(url).then(() => {
        window.scrollTo(0, 0); 
      });
    }
  }

  scrollToFeaturedProducts() {
    const sectionElement = this.elementRef.nativeElement.querySelector('#featuredProductsSection');
    sectionElement.scrollIntoView({ behavior: 'smooth' });
  }
  
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
