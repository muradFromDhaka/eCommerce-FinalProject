import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/public/home/home.component';
import { ProductListComponent } from './components/public/product-list/product-list.component';
import { CategoryComponent } from './components/public/category/category.component';
import { LoginComponent } from './components/public/login/login.component';
import { SignupComponent } from './components/public/signup/signup.component';
import { ProfileComponent } from './components/user/profile/profile.component';
import { CartComponent } from './components/user/cart/cart.component';
import { CheckoutComponent } from './components/user/checkout/checkout.component';
import { OrdersComponent } from './components/user/orders/orders.component';
import { WishlistComponent } from './components/user/wishlist/wishlist.component';
import { DashboardComponent } from './components/admin/dashboard/dashboard.component';
import { ManageCategoriesComponent } from './components/admin/manage-categories/manage-categories.component';
import { ManageOrdersComponent } from './components/admin/manage-orders/manage-orders.component';
import { ManageUsersComponent } from './components/admin/manage-users/manage-users.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LayoutComponent } from './components/admin/layout/layout.component';
import { UserLayoutComponent } from './components/user/user-layout/user-layout.component';
import { UserNavbarComponent } from './components/user/user-navbar/user-navbar.component';
import { PublicNavbarComponent } from './components/public/public-navbar/public-navbar.component';
import { ProductFormComponent } from './components/admin/products/product-form/product-form.component';
import { ProductViewComponent } from './components/admin/products/product-view/product-view.component';
import { AdminProductListComponent } from './components/admin/products/admin-product-list/admin-product-list.component';
import { VendorDashboardComponent } from './components/user/vendor-dashboard/vendor-dashboard.component';
import { CategoryListComponent } from './components/admin/category/category-list/category-list.component';
import { CategoryFormComponent } from './components/admin/category/category-form/category-form.component';
import { CategoryDetailsComponent } from './components/admin/category/category-details/category-details.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProductListComponent,
    CategoryComponent,
    LoginComponent,
    SignupComponent,
    ProfileComponent,
    CartComponent,
    CheckoutComponent,
    OrdersComponent,
    WishlistComponent,
    DashboardComponent,
    ManageCategoriesComponent,
    ManageOrdersComponent,
    ManageUsersComponent,
    LayoutComponent,
    UserLayoutComponent,
    UserNavbarComponent,
    PublicNavbarComponent,
    ProductFormComponent,
    ProductViewComponent,
    AdminProductListComponent,
    VendorDashboardComponent,
    CategoryListComponent,
    CategoryFormComponent,
    CategoryDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
