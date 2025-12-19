import { Component, HostListener } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-public-navbar',
  templateUrl: './public-navbar.component.html',
  styleUrls: ['./public-navbar.component.scss']
})
export class PublicNavbarComponent {

isLoggedIn: boolean = false;
  isAdmin: boolean = false;
  cartItemCount: number = 3; // This should come from a service
  userName: string = 'User';

  constructor(private router: Router) {}

  ngOnInit(): void {
    // Check authentication status
    this.checkAuthStatus();
    
    // For demo purposes - in real app, get from auth service
    const token = localStorage.getItem('token');
    if (token) {
      this.isLoggedIn = true;
      // Check if user is admin - this should come from your auth service
      const userRole = localStorage.getItem('userRole');
      this.isAdmin = userRole === 'admin';
      
      // Get user name from localStorage or service
      this.userName = localStorage.getItem('userName') || 'User';
    }
  }

  checkAuthStatus(): void {
    // This should connect to your authentication service
    // For now, we'll use localStorage
    this.isLoggedIn = !!localStorage.getItem('token');
    
    if (this.isLoggedIn) {
      const userRole = localStorage.getItem('userRole');
      this.isAdmin = userRole === 'admin';
      this.userName = localStorage.getItem('userName') || 'User';
    }
  }

  logout(): void {
    // Clear authentication data
    localStorage.removeItem('token');
    localStorage.removeItem('userRole');
    localStorage.removeItem('userName');
    
    // Update UI
    this.isLoggedIn = false;
    this.isAdmin = false;
    this.userName = 'User';
    
    // Redirect to home
    this.router.navigate(['/home']);
  }

  // Update cart count (this would be called from cart service)
  updateCartCount(count: number): void {
    this.cartItemCount = count;
  }

}
