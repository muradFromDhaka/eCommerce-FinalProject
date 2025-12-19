import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Wishlist } from '../model/wishlist.model';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {
  private apiUrl = 'http://localhost:8080/api/wish';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Wishlist[]> {
    return this.http.get<Wishlist[]>(this.apiUrl);
  }

  getById(id: number): Observable<Wishlist> {
    return this.http.get<Wishlist>(`${this.apiUrl}/${id}`);
  }

  create(wishlist: Wishlist): Observable<Wishlist> {
    return this.http.post<Wishlist>(this.apiUrl, wishlist);
  }

  update(wishlist: Wishlist): Observable<Wishlist> {
    return this.http.put<Wishlist>(`${this.apiUrl}/${wishlist.id}`, wishlist);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
