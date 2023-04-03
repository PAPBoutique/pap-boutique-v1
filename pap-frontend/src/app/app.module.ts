import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateProductComponent } from './product/page/create-product/create-product.component';
import { ListProductComponent } from './product/page/list-product/list-product.component';

@NgModule({
  declarations: [
    AppComponent,
    CreateProductComponent,
    ListProductComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
