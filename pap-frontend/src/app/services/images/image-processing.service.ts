import { Injectable } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { FileHandle } from 'src/app/models/product/file-handle.model';
import { Product } from 'src/app/models/product/product';

@Injectable({
  providedIn: 'root'
})
export class ImageProcessingService {

  constructor( private sanitizer : DomSanitizer) { }

  public createImages(product: Product) {
    const productImage: any[] = product.productImages; 
    const productImagesToFileHandle: FileHandle[] = [];
  
    if (productImage && productImage.length > 0) {
      for (let i = 0; i < productImage.length; i++) {
        const imageFileData = productImage[i];
  
        const imageBlob = this.dataURItoBlob(imageFileData.picByte, imageFileData.type);
        const imageFile = new File([imageBlob], imageFileData.name, { type: imageFileData.type });
  
        const finalFileHandle: FileHandle = {
          file: imageFile,
          url: this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(imageFile)),
        };
  
  
        productImagesToFileHandle.push(finalFileHandle);
      }
      console.log(productImagesToFileHandle);

    }
  
    product.productImages = productImagesToFileHandle;
    return product;
  }
  public dataURItoBlob(picByte : any, imageType : any) {
    const byteString = window.atob(picByte);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const int8Array = new Uint8Array(arrayBuffer);

    for(let i = 0; i < byteString.length; i++) {
      int8Array[i] = byteString.charCodeAt(i);
    }

    const blob = new Blob([int8Array], { type: imageType});
    return blob;
  }

}
