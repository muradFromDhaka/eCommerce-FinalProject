import { Component, HostListener } from '@angular/core';
import { Router } from '@angular/router';
import { CategoryResponse } from 'src/app/model/category.model';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { CartService } from 'src/app/services/cart.service';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-public-navbar',
  templateUrl: './public-navbar.component.html',
  styleUrls: ['./public-navbar.component.scss']
})
export class PublicNavbarComponent {

 isLoggedIn: boolean = false;
  isAdmin: boolean = false;
  userName: string = '';
  cartCount: number = 0;
  mobileMenuOpen: boolean = false;
 
  categories: CategoryResponse[]=[];

  constructor(
    private authService: AuthServiceService,
    private cartService: CartService,
    private categoryService: CategoryService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.categoryService.getAllCategories().subscribe((category) => this.categories = category)
    // Check authentication status
    // this.authService.currentUser.subscribe(user => {
    //   this.isLoggedIn = !!user;
    //   if (user) {
    //     this.userName = user.name || 'User';
    //     this.isAdmin = user.role === 'ADMIN';
    //   }
    // });

    // Get cart count
    // this.cartService.cartItems.subscribe(items => {
    //   this.cartCount = items.reduce((total, item) => total + item.quantity, 0);
    // });

    // Check initial auth state
    this.checkAuthStatus();
  }

  checkAuthStatus(): void {
    const token = localStorage.getItem('token');
    const userData = localStorage.getItem('user');
    
    if (token && userData) {
      try {
        const user = JSON.parse(userData);
        this.isLoggedIn = true;
        this.userName = user.name;
        this.isAdmin = user.role === 'ADMIN';
      } catch (e) {
        console.error('Error parsing user data:', e);
      }
    }
  }

  logout(): void {
    this.authService.logout();
    this.isLoggedIn = false;
    this.userName = '';
    this.isAdmin = false;
    // this.cartService.clearCart();
    this.router.navigate(['/login']);
  }

  toggleMobileMenu(): void {
    this.mobileMenuOpen = !this.mobileMenuOpen;
  }

  @HostListener('window:resize', ['$event'])
  onResize(event: any): void {
    if (event.target.innerWidth > 576) {
      this.mobileMenuOpen = false;
    }
  }
}
