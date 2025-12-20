import { Component } from '@angular/core';
import { CategoryResponse } from 'src/app/model/category.model';
import { DealResponse } from 'src/app/model/deal.model';
import { ProductList } from 'src/app/model/product.model';
import { CartService } from 'src/app/services/cart.service';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';
import { WishlistService } from 'src/app/services/wishlist.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
 // Carousel State
  currentSlide = 0;
  
  // Categories Data
  categories: CategoryResponse[] = [];

  // Today's Deals Data
  todaysDeals: DealResponse[] = [];

  // Featured Products Data
  products: ProductList[] = [];

  // Recommended Products
  recommendedProducts: ProductList[] = [];

  // Carousel interval reference
  private carouselInterval: any;

  constructor(
    private productService: ProductService,
    private categoryService: CategoryService,
    private cartService: CartService,
    private wishlistService: WishlistService
  ) {}

  ngOnInit(): void {
    this.startCarousel();
    this.loadProducts();
  }

  loadProducts(){
    this.productService.getAllProducts().subscribe((value) => this.products = value);
    this.productService.getAllProducts().subscribe((value) => this.recommendedProducts = value);
  }
  // Carousel Methods
  startCarousel(): void {
    this.carouselInterval = setInterval(() => {
      this.nextSlide();
    }, 5000);
  }

  nextSlide(): void {
    this.currentSlide = (this.currentSlide + 1) % 3;
  }

  prevSlide(): void {
    this.currentSlide = this.currentSlide === 0 ? 2 : this.currentSlide - 1;
  }

  goToSlide(index: number): void {
    this.currentSlide = index;
    this.resetCarousel();
  }

  resetCarousel(): void {
    clearInterval(this.carouselInterval);
    this.startCarousel();
  }

  // Product Actions
  addToCart(product: any): void {
    this.cartService.addToCart({
      id: product.id,
      name: product.name,
      price: product.price,
      quantity: 1,
      image: product.image
    });
    
    // Show notification (you can implement toast service)
    alert(`${product.name} added to cart!`);
  }

  addToWishlist(product: any): void {
    this.wishlistService.addToWishlist({
      id: product.id,
      name: product.name,
      price: product.price,
      image: product.image
    });
    
    alert(`${product.name} added to wishlist!`);
  }

  ngOnDestroy(): void {
    if (this.carouselInterval) {
      clearInterval(this.carouselInterval);
    }
  }


}
