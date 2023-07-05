import { HttpErrorResponse } from '@angular/common/http';
import { Component, ElementRef} from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { map } from 'rxjs';
import { Product } from 'src/app/models/product/product';
import { ImageProcessingService } from 'src/app/services/images/image-processing.service';
import { ProductService } from 'src/app/services/product/product-service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent {

  products : Product[] = []
  ngOnInit(): void {
    this.getAllProducts();
    
  }
 
  constructor(private productService : ProductService, private imageProcessingService : ImageProcessingService, 
              private sanitizer : DomSanitizer,
              private router : Router,
              private elementRef: ElementRef) {}

  getAllProducts() {
    this.productService.getAllProducts()
      .pipe(
        map((x: Product[], i) => x.map((product: Product) => this.imageProcessingService.createImages(product)))
      )
      .subscribe(
        (resp: Product[]) => {
          this.products = resp;
          console.log('Products Data : ', this.products);
        },
        (error: HttpErrorResponse) => {
          console.log(error);
        }
      );
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
  
}
