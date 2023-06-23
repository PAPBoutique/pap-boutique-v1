import { Component, EventEmitter, Input, Output } from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';


@Component({
  selector: 'app-product-images',
  templateUrl: './product-images.component.html',
  styleUrls: ['./product-images.component.css']
})
export class ProductImagesComponent {

  @Input() visible : boolean =false ;
  @Output() closeDialog = new EventEmitter<any>() ;
  @Input() selectedImages!: any[];

 
  constructor(private sanitizer: DomSanitizer) {}
  
  
  getSafeImageUrl(url: string): SafeUrl {
    return this.sanitizer.bypassSecurityTrustUrl(url);
  }

  
  onHide(e:any){
    this.closeDialog.emit();
  }

}
