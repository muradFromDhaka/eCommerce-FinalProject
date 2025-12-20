import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { debounceTime, Subject } from 'rxjs';
import { ProductList } from 'src/app/model/product.model';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent {
products: ProductList[] = [];
  loading: boolean = false;
  errorMessage: string = '';
  searchControl: FormControl = new FormControl('');

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    // প্রথমে সব প্রোডাক্ট load করি
    this.fetchProducts();

    // Reactive form control value changes
    this.searchControl.valueChanges
      .pipe(debounceTime(300))
      .subscribe(term => {
        if (term && term.trim() !== '') {
          this.searchProducts(term);
        } else {
          this.fetchProducts();
        }
      });
  }

  fetchProducts(): void {
    this.loading = true;
    this.productService.getAllProducts().subscribe({
      next: (data) => {
        this.products = data;
        this.loading = false;
      },
      error: (err) => {
        this.errorMessage = 'Failed to load products';
        console.error(err);
        this.loading = false;
      }
    });
  }

  searchProducts(name: string): void {
    this.loading = true;
    this.productService.searchProducts(name).subscribe({
      next: (data) => {
        this.products = data;
        this.loading = false;
      },
      error: (err) => {
        this.errorMessage = 'Failed to search products';
        console.error(err);
        this.loading = false;
      }
    });
  }

}
