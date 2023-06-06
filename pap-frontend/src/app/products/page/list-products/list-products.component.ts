import { Component, ViewChild, ViewChildren } from '@angular/core';
import { ProductService } from '../../../services/product/product-service';
import { Product } from '../../../models/product/product';
import { PageContent } from 'src/app/shared-components/service/pageContent';

@Component({
  selector: 'app-list-products',
  templateUrl: './list-products.component.html',
  styleUrls: ['./list-products.component.css']
})
export class ListProductsComponent {
  constructor(private productService: ProductService) { }
  products: Product[] = [];
  totalProducts: number = 0;

  ngOnInit() {
  }
  deleteProduct = (id: number) => {
    return this.productService.deleteProduct(id);
  }

  fetchProducts(page: number = 0, size: number = 5, filterValue: String = "") {
    this.productService.getProductsByPage(page, size, filterValue).subscribe((productsPage: PageContent<Product>) => {
      if (productsPage && productsPage.content) {
        this.products = productsPage.content;
        this.totalProducts = productsPage.totalElements;
      }
    });
  }
  onTableChange(event: { page: number, size: number, filterValue: String }) {
    this.fetchProducts(event.page, event.size, event.filterValue);
  }

}
