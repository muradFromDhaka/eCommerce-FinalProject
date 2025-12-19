import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProductListDto } from 'src/app/model/product.model';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-admin-product-list',
  templateUrl: './admin-product-list.component.html',
  styleUrls: ['./admin-product-list.component.scss']
})
export class AdminProductListComponent {
 productList!: ProductListDto[];

 constructor(
  private productService: ProductService,
  private cartService: CartService,
  private router: Router
 ){}

 ngOnInit(){
   this.loadProducts();
 }

 loadProducts(){
  this.productService.getAll().subscribe((product) => this.productList = product)
 }


addToCart(product: ProductListDto) {
  const payload = {
    productId: product.id,
    quantity: 1
  };

  // this.cartService.addToCart(payload).subscribe({
  //   next: (res) => {
  //     console.log('Cart updated:', res);
  //     alert(`"${product.name}" added to cart`);
  //   },
  //   error: (err) => {
  //     console.error(err);
  //     alert('Failed to add product to cart');
  //   }
  // });
}

 deleteProduct(id: number){
  this.productService.delete(id).subscribe(() => {this.loadProducts()})
 }

}
