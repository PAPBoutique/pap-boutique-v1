import { Component, ElementRef, EventEmitter, Input, Output, Renderer2, ViewChild } from '@angular/core';
import { FormBuilder, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Product } from 'src/app/models/product/product';
import { MessageService } from "primeng/api";
import { ProductService } from 'src/app/services/product/product-service';
import { DomSanitizer } from '@angular/platform-browser';
import { FileHandle } from 'src/app/models/product/file-handle.model';
import { HttpErrorResponse } from '@angular/common/http';
import { Dialog } from 'primeng/dialog';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent {


  
  ProductForm = this.fb.group({
    name: ['', Validators.required],
    Description: ['', Validators.required],
    Price: ['', Validators.required],
    Quantity: ['', Validators.required],
  });

  product: Product = {
    name: '',
    description: '',
    quantity: 0,
    price: 0,
    productImages: []
  };

  constructor(
    private sanitizer: DomSanitizer,
    private messageService: MessageService,
    private fb: FormBuilder,
    private productService: ProductService,
    private router: Router,
    private renderer : Renderer2) { }

  @Input() visible?: boolean;
  @Output() closeDialog = new EventEmitter<any>() ;
  @ViewChild('addDialog') addDialog! : Dialog;
  loading: boolean = false;

  onFileSelected(event: any) {
    if (event.files && event.files.length) {
      const files: File[] = event.files;
      console.log(files);
  
      for (let i = 0; i < files.length; i++) {
        const file: File = files[i];
        const fileHandle: FileHandle = {
          file: file,
          url: this.sanitizer.bypassSecurityTrustUrl(
            window.URL.createObjectURL(file)
          ),
        };
        this.product.productImages.push(fileHandle);
      }
    }
  }
  
  ngAfterViewInit(){
    this.addDialog.onShow.subscribe(() => {
      const closeBtn = this.addDialog.el.nativeElement.querySelector(".p-dialog-header-close");
      closeBtn.id="closeAddP";
    });
  }



  
  addProduct(productForm: FormGroupDirective) {
    const formData = this.prepareFormDataForProduct(this.product);
    this.productService.addProductWithPicture(formData).subscribe(
      (response: Product) => {
        console.log(response);
        productForm.reset();
        this.product.productImages = [];
        this.showsuccessMessage(response.name);
        this.loading = true ;
        setTimeout(() => {
          console.log("after loading");
          window.location.reload();
        }, 2000);
      },
      
      (error: HttpErrorResponse) => {
        this.showErrorMessage();
        console.log(error);
      }
    );
  }

  prepareFormDataForProduct(product: Product): FormData {
    const uploadImageData = new FormData();
    uploadImageData.append(
      'product',
      new Blob([JSON.stringify(product)], { type: 'application/json' })
    );

    for (let i = 0; i < this.product.productImages.length; i++) {
      const fileHandle: FileHandle = this.product.productImages[i];
      uploadImageData.append(
        'imageFile',
        fileHandle.file,
        fileHandle.file.name
      );
    }

    return uploadImageData;
  }

  onHide(e:any){
    this.closeDialog.emit();
  }

  showErrorMessage() {
    this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Sorry, product creation failed. Try again later.' });
  }
  showsuccessMessage(productName : string) {
    this.messageService.add({ severity: 'success', summary: 'Congratulations, your product has been created!', detail: productName + ' has been created' });
  }

  closeToast() {
    this.router.navigate(['/products/list'])
  }


}
