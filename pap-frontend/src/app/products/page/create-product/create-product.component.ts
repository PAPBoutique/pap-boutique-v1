import { Component, ElementRef, EventEmitter, Input, Output, Renderer2, ViewChild } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Product } from 'src/app/models/product/product';
import { MessageService } from "primeng/api";
import { ProductService } from 'src/app/services/product/product-service';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent {
  constructor(private messageService: MessageService,
    private fb: FormBuilder,
    private productService: ProductService,
    private router: Router,
    private renderer : Renderer2) { }

  @Input() visible?: boolean;
  @Output() closeDialog = new EventEmitter<any>() ;
  loading: boolean = false;
  @ViewChild('submitButton' , {read : ElementRef}) submitButton!: ElementRef<any>;

  ngAfterViewInit() {
    const btn = this.submitButton.nativeElement.firstChild ;
    this.renderer.setAttribute(btn,'id','cproduct-btn-submit');
  }

  onHide(e:any){
    this.closeDialog.emit();
  }

  ProductForm = this.fb.group({
    name: ['', Validators.required],
    Description: ['', Validators.required],
    Price: ['', Validators.required],
    Quantity: ['', Validators.required],
    myfile: ['']
  });

  products: Product[] = [
    {
      name: '',
      description: '',
      quantity: 0,
      price: 0,
    },
  ]
  CreateProduct() {
    this.productService.createProduct(this.products).subscribe(
      response => {
        console.log(response)
        this.showsuccessMessage();
        this.loading = true ;
        setTimeout(() => {
          console.log("after loading");
          window.location.reload();
        }, 2000);
      },
      error => {
        console.log(error);
        this.showErrorMessage();
      }
    )
  }

  showErrorMessage() {
    this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Sorry, product creation failed. Try again later.' });
  }
  showsuccessMessage() {
    const product = this.products[0];
    this.messageService.add({ severity: 'success', summary: 'Congratulations, your product has been created!', detail: product.name + ' has been created' });
  }

  closeToast() {
    this.router.navigate(['/products/list'])
  }


}
