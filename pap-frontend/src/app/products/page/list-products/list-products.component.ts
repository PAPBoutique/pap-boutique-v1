import { Component, ViewChild, ViewChildren } from '@angular/core';
import { ProductService } from '../../../services/product/product-service';
import { Product } from '../../../models/product/product';
import { PageContent } from 'src/app/shared-components/service/pageContent';
import { ImageProcessingService } from 'src/app/services/images/image-processing.service';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-list-products',
  templateUrl: './list-products.component.html',
  styleUrls: ['./list-products.component.css']
})
export class ListProductsComponent {
  constructor(private productService: ProductService, private imageProcessingService: ImageProcessingService) { }
  products: Product[] = [];
  totalProducts: number = 0;

  ngOnInit() {
  }
  deleteProduct = (id: number) => {
    return this.productService.deleteProduct(id);
  }

  fetchProducts(page: number = 0, size: number = 5, filterValue: String = "") {
    this.productService.getProductsByPage(page, size, filterValue)
  .pipe(
    map((pageContent: PageContent<Product>) => {
      if (pageContent && pageContent.content) {     

        return pageContent.content.map((product: Product) => this.imageProcessingService.createImages(product));
       
      }
      return [];
    })
  )
  .subscribe((products: Product[]) => {
    if (products) {
      this.products = products;
      this.totalProducts = products.length;
    }
  });

  }
  onTableChange(event: { page: number, size: number, filterValue: String }) {
    this.fetchProducts(event.page, event.size, event.filterValue);
  }

}
