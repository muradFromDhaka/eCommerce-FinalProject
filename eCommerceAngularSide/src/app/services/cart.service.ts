import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { CartDto } from '../model/cart.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {
 
  private apiUrl = 'http://localhost:8080/api/carts';

  constructor(private http: HttpClient) {}

  private cartCount = new BehaviorSubject<number>(0);

  getCartCount() {
    return this.cartCount.asObservable();
  }

  updateCartCount(count: number) {
    this.cartCount.next(count);
  }




  getAllCarts(): Observable<CartDto[]> {
    return this.http.get<CartDto[]>(this.apiUrl);
  }

  getCartById(id: number): Observable<CartDto> {
    return this.http.get<CartDto>(`${this.apiUrl}/${id}`);
  }

  addToCart(cart: any): Observable<CartDto> {
    return this.http.post<CartDto>(this.apiUrl, cart);
  }

  updateCart(id: number, cart: any): Observable<CartDto> {
    return this.http.put<CartDto>(`${this.apiUrl}/${id}`, cart);
  }

  deleteCart(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}

