import { Component } from '@angular/core';
import { Category } from 'src/app/model/category.model';
import { Product, ProductListDto } from 'src/app/model/product.model';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
products!: ProductListDto[];

featuredCategories: Category[] = [];
  loading: boolean = true;
  error: string | null = null;



constructor(
  private productService: ProductService,
  private categoryService: CategoryService){

}

ngOnInit(){
  this.productService.getAll().subscribe((value) => this.products = value);
  this.loadFeaturedCategories();
}

 loadFeaturedCategories(): void {
    this.loading = true;
    this.error = null;
    
    this.categoryService.getAllCategories().subscribe({
      next: (data) => {
        // প্রথম ৪টি ক্যাটেগরি ফিচার্ড হিসেবে নিন
        this.featuredCategories = data.slice(0, 4);
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load categories.';
        this.loading = false;
        console.error('Error loading categories:', err);
        
        // Fallback data if API fails
        this.loadFallbackCategories();
      }
    });
  }

  loadFallbackCategories(): void {
    this.featuredCategories = [
      {
        id: 1,
        name: 'Electronics',
        description: 'Latest gadgets and electronic devices',
        imageUrl: 'assets/electronics.jpg'
      },
      {
        id: 2,
        name: 'Fashion',
        description: 'Trendy clothing and accessories',
        imageUrl: 'assets/fashion.jpg'
      },
      {
        id: 3,
        name: 'Home & Kitchen',
        description: 'Everything for your home',
        imageUrl: 'assets/home.jpg'
      },
      {
        id: 4,
        name: 'Books',
        description: 'Best selling books and novels',
        imageUrl: 'assets/books.jpg'
      }
    ];
    this.loading = false;
  }

  // Loading skeleton for better UX
  getSkeletonArray(count: number): any[] {
    return new Array(count).fill(0);
  }



}
