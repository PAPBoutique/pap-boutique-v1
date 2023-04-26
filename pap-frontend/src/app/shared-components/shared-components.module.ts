import { NgModule } from '@angular/core';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { ReactiveFormsModule } from '@angular/forms'; 
import { FileUploadModule } from "primeng/fileupload";
import {FormsModule} from '@angular/forms'; 
import {InputTextareaModule} from 'primeng/inputtextarea';
import {InputTextModule} from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import { ConfirmPopupModule } from "primeng/confirmpopup";
import { ToastModule } from "primeng/toast";

import { CardModule } from 'primeng/card';
import { NavbarComponent } from './navbar/navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [
    PageNotFoundComponent,
    NavbarComponent,
    FooterComponent
    
  ],
  exports: [
    PageNotFoundComponent,
    NavbarComponent,
    ReactiveFormsModule,
    FileUploadModule,
    FormsModule,
    InputTextareaModule,
    InputTextModule,
    ButtonModule,
    RippleModule,
    ConfirmPopupModule,
    ToastModule,
    CardModule,
    FooterComponent
    
  ],
  imports: [
    ReactiveFormsModule,
    FileUploadModule,
    FormsModule,
    InputTextareaModule,
    InputTextModule,
    ButtonModule,
    RippleModule,
    ConfirmPopupModule,
    ToastModule,
    CardModule,
    RouterModule


  ]
})
export class SharedComponentsModule { }
