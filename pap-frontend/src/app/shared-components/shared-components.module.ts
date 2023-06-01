import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { ToastModule } from 'primeng/toast';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MessagesModule } from 'primeng/messages';
import { PaginatorModule } from 'primeng/paginator';
import { ConfirmationService, MessageService } from 'primeng/api';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { FooterComponent } from './footer/footer.component';
import { NavbarComponent } from './navbar/navbar.component';
import { RippleModule } from 'primeng/ripple';
import { RouterModule } from '@angular/router';
import { FileUploadModule } from "primeng/fileupload";
import { DialogModule } from 'primeng/dialog';
import {InputTextareaModule} from 'primeng/inputtextarea';
import { ConfirmPopupModule } from "primeng/confirmpopup";
import { CardModule } from 'primeng/card';
import { SidebarComponent } from './sidebar/sidebar.component';
import { LoaderComponent } from './loader/loader.component';


@NgModule({
  declarations: [
    PageNotFoundComponent,
    FooterComponent,
    NavbarComponent,
    SidebarComponent,
    LoaderComponent
  ],
  exports: [
    PageNotFoundComponent,
    TableModule,
    InputTextModule,
    FormsModule,
    MessagesModule,
    ToastModule,
    ButtonModule,
    PaginatorModule,
    ConfirmDialogModule,
    FooterComponent,
    NavbarComponent,
    CardModule,
    ReactiveFormsModule,
    FileUploadModule,
    FormsModule,
    InputTextareaModule,
    RippleModule,
    SidebarComponent,
    DialogModule,
    LoaderComponent
  ],
  imports: [
    CommonModule,
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
    DialogModule,
    RouterModule
  ],
  providers:[MessageService,ConfirmationService]
})
export class SharedComponentsModule { }
