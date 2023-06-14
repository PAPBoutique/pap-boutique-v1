import { Component, ElementRef, EventEmitter, Input, Output, Renderer2, ViewChild } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Product } from 'src/app/models/product/product';
import { MessageService } from 'primeng/api';
import { ProductService } from 'src/app/services/product/product-service';

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
    private renderer : Renderer2
  ) { }
  loading: boolean = false;

  ProductForm = this.fb.group({
    name: ['', Validators.required],
    Description: ['', Validators.required],
    Price: ['', Validators.required],
    Quantity: ['', Validators.required],
    myfile: ['']
  });

  product!: Product;

  @Input() selectedProduct!: Product;
  @Input() visible : boolean =false ;
  @Output() closeDialog = new EventEmitter<any>() ;


  onHide(e:any){
    this.closeDialog.emit();
  }

  updateProduct(product: Product) {

    this.productService.updateProduct(product).subscribe(
      (data: Product) => {
        console.log(data);
        this.showsuccessMessage();
        this.loading = true ;
        console.log(this.loading);
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
