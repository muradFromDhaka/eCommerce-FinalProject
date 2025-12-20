import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CategoryRequest, CategoryResponse } from '../model/category.model';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  
  private apiUrl = 'http://localhost:8080/api/categories';

    constructor(private http: HttpClient) {}

  // Get all categories
  getAllCategories(): Observable<CategoryResponse[]> {
    return this.http.get<CategoryResponse[]>(this.apiUrl);
  }

  // Get category by ID
  getCategoryById(id: number): Observable<CategoryResponse> {
    return this.http.get<CategoryResponse>(`${this.apiUrl}/${id}`);
  }

  // Create new category
  createCategory(category: CategoryRequest): Observable<CategoryResponse> {
    return this.http.post<CategoryResponse>(this.apiUrl, category);
  }

  // Update category
  updateCategory(id: number, category: CategoryRequest): Observable<CategoryResponse> {
    return this.http.put<CategoryResponse>(`${this.apiUrl}/update/${id}`, category);
  }

  // Delete category
  deleteCategory(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }
}
