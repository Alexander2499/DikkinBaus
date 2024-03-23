import { BrowserModule } from '@angular/platform-browser';
import {Injector, NgModule} from '@angular/core';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {LoginPageComponent} from "./login-page/login-page.component";
import {RouterOutlet} from "@angular/router";
import {FormsModule} from "@angular/forms";
import { createCustomElement } from '@angular/elements';

@NgModule({
  declarations: [
    LoginPageComponent
  ],
  imports: [
    HttpClientModule,
    RouterOutlet,
    FormsModule
  ],
  exports: [
    LoginPageComponent
  ],
  providers: [],
})
export class HomeModule {

}
