import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/public/home/home.component';
import { CategoryComponent } from './components/public/category/category.component';
import { LoginComponent } from './components/public/login/login.component';
import { ProductListComponent } from './components/public/product-list/product-list.component';
import { ProductDetailsComponent } from './components/public/product-details/product-details.component';
import { SignupComponent } from './components/public/signup/signup.component';
import { LayoutComponent } from './components/admin/layout/layout.component';
import { DashboardComponent } from './components/admin/dashboard/dashboard.component';
import { ManageCategoriesComponent } from './components/admin/manage-categories/manage-categories.component';
import { OrdersComponent } from './components/user/orders/orders.component';
import { ManageOrdersComponent } from './components/admin/manage-orders/manage-orders.component';
import { ManageUsersComponent } from './components/admin/manage-users/manage-users.component';
import { UserLayoutComponent } from './components/user/user-layout/user-layout.component';
import { ProfileComponent } from './components/user/profile/profile.component';
import { CheckoutComponent } from './components/user/checkout/checkout.component';
import { CartComponent } from './components/user/cart/cart.component';
import { WishlistComponent } from './components/user/wishlist/wishlist.component';
import { ProductFormComponent } from './components/admin/products/product-form/product-form.component';
import { ProductViewComponent } from './components/admin/products/product-view/product-view.component';
import { AdminProductListComponent } from './components/admin/products/admin-product-list/admin-product-list.component';
import { CategoryListComponent } from './components/admin/category/category-list/category-list.component';
import { CategoryFormComponent } from './components/admin/category/category-form/category-form.component';
import { CategoryDetailsComponent } from './components/admin/category/category-details/category-details.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'productList' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'category', component: CategoryComponent },
  { path: 'productList', component: ProductListComponent },
  { path: 'productDetails/:id', component: ProductDetailsComponent },

  {
    path: 'admin',
    component: LayoutComponent,
    children: [
      { path: '', pathMatch: 'full', redirectTo: 'dashboard' },
      { path: 'dashboard', component: DashboardComponent },
      { path: 'category', component: ManageCategoriesComponent },
      { path: 'order', component: ManageOrdersComponent },
      { path: 'user', component: ManageUsersComponent },

      { path: 'adminProductList', component: AdminProductListComponent },
      { path: 'addProduct', component: ProductFormComponent },
      { path: 'editProduct/:id', component: ProductFormComponent },
      { path: 'viewProduct/:id', component: ProductViewComponent },

      { path: 'adminCategoryList', component: CategoryListComponent },
      { path: 'addCategory', component: CategoryFormComponent },
      { path: 'editCategory/:id', component: CategoryFormComponent },
      { path: 'category/:id', component: CategoryDetailsComponent }
    ],
  },

  {
    path: 'user',
    component: UserLayoutComponent,
    children: [
      { path: '', pathMatch: 'full', redirectTo: 'profile' },
      { path: 'profile', component: ProfileComponent },
      { path: 'order', component: OrdersComponent },
      { path: 'check', component: CheckoutComponent },
      { path: 'cart', component: CartComponent },
      { path: 'wish', component: WishlistComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
