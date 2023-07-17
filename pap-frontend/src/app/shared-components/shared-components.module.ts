import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

////////////
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
import { DropdownModule } from 'primeng/dropdown';
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
import { PasswordModule } from "primeng/password";
import { ImageModule } from 'primeng/image';
import { OverlayPanelModule } from 'primeng/overlaypanel';
import { ConfirmDialogComponent } from './confirm-dialog/confirm-dialog.component';
import { ChartModule } from 'primeng/chart';
import { PanelModule } from 'primeng/panel';
import { MenubarModule } from "primeng/menubar";
import { InputMaskModule } from 'primeng/inputmask';




@NgModule({
  declarations: [
    PageNotFoundComponent,
    FooterComponent,
    NavbarComponent,
    SidebarComponent,
    LoaderComponent,
    
    ConfirmDialogComponent
  ],
  exports: [
    MenubarModule,
    PageNotFoundComponent,
    ChartModule,
    OverlayPanelModule,
    PanelModule,
    TableModule,
    InputTextModule,
    FormsModule,
    MessagesModule,
    ToastModule,
    PasswordModule,
    InputMaskModule,
    ButtonModule,
    PaginatorModule,
    ConfirmDialogModule,
    FooterComponent,
    NavbarComponent,
    DropdownModule,
    CardModule,
    ReactiveFormsModule,
    FileUploadModule,
    FormsModule,
    InputTextareaModule,
    RippleModule,
    SidebarComponent,
    DialogModule,
    LoaderComponent,
    ImageModule,
    ConfirmDialogComponent,
  ],
  imports: [
    InputMaskModule,

    MenubarModule,
    OverlayPanelModule,
    ChartModule,
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
    ImageModule,
    PasswordModule,
    DropdownModule,
    DialogModule,
    PanelModule,
    RouterModule,
    ConfirmDialogModule
  ],
  providers:[MessageService,ConfirmationService]
})
export class SharedComponentsModule { }
