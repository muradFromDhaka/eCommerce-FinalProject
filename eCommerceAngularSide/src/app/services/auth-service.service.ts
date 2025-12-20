import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

 private isLoggedInSubject = new BehaviorSubject<boolean>(false);
  
  constructor(
    private http: HttpClient,
    private router: Router
  ) {
    this.checkAuthStatus();
  }

  isAuthenticated(): Observable<boolean> {
    return this.isLoggedInSubject.asObservable();
  }

  getUserName(): string {
    return localStorage.getItem('userName') || '';
  }

  isAdmin(): boolean {
    return localStorage.getItem('role') === 'ADMIN';
  }

  checkAuthStatus(): void {
    const token = localStorage.getItem('token');
    this.isLoggedInSubject.next(!!token);
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('userName');
    localStorage.removeItem('role');
    this.isLoggedInSubject.next(false);
    this.router.navigate(['/login']);
  }
}