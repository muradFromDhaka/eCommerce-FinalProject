import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CartDto } from '../model/cart.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {
 
  private apiUrl = 'http://localhost:8080/api/carts';

  constructor(private http: HttpClient) {}

  getAllCarts(): Observable<CartDto[]> {
    return this.http.get<CartDto[]>(this.apiUrl);
  }

  getCartById(id: number): Observable<CartDto> {
    return this.http.get<CartDto>(`${this.apiUrl}/${id}`);
  }

  createCart(cart: any): Observable<CartDto> {
    return this.http.post<CartDto>(this.apiUrl, cart);
  }

  updateCart(id: number, cart: any): Observable<CartDto> {
    return this.http.put<CartDto>(`${this.apiUrl}/${id}`, cart);
  }

  deleteCart(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}

