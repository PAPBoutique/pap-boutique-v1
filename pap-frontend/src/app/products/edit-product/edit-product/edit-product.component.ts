import { Component, Input } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Product } from 'src/app/models/product/product';
import { MessageService } from 'primeng/api';
import { ProductService } from 'src/app/services/product/product-service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent {

  constructor(
    private messageService: MessageService,
    private fb: FormBuilder,
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ProductForm = this.fb.group({
    name: ['', Validators.required],
    Description: ['', Validators.required],
    Price: ['', Validators.required],
    Quantity: ['', Validators.required],
    myfile: ['']
  });

  product!: Product;

  @Input() selectedProduct!: Product;
  updateProduct(product: Product) {
    
      this.productService.updateProduct(product).subscribe(
        (data: Product) => {
          console.log(data);
          this.showsuccessMessage();
          setTimeout(() => {
            window.location.reload();
          }, 3000);
        },
        (error: any) => {
          console.log(error);
          this.showErrorMessage();
        }
      );
    
  }

  showsuccessMessage() {
    this.messageService.add({ severity: 'success', summary: 'Your product has been updated!', detail: this.selectedProduct.name + ' has been updated' });
  }

  showErrorMessage() {
    this.messageService.add({ severity: 'error', summary: 'Error occurred', detail: 'There was an error while updating the product.' });
  }

  closeToast() {
    window.location.reload();
  }
}
