import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProductDetails, ProductList,  ProductRequest, ProductResponse } from '../model/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiUrl = 'http://localhost:8080/api/products';

   constructor(private http: HttpClient) {}

  // Get all products (lightweight list)
  getAllProducts(): Observable<ProductList[]> {
    return this.http.get<ProductList[]>(this.apiUrl);
  }

  // Get product by ID (details)
  getProductById(id: number): Observable<ProductDetails> {
    return this.http.get<ProductDetails>(`${this.apiUrl}/${id}`);
  }

  // Create product
  createProduct(product: ProductRequest): Observable<ProductResponse> {
    return this.http.post<ProductResponse>(this.apiUrl, product);
  }

  // Update product
  updateProduct(id: number, product: ProductRequest): Observable<ProductResponse> {
    return this.http.put<ProductResponse>(`${this.apiUrl}/${id}`, product);
  }

  // Delete product
  deleteProduct(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  // Search products by name
  searchProducts(name: string): Observable<ProductResponse[]> {
    return this.http.get<ProductResponse[]>(`${this.apiUrl}/searchProducts`, {
      params: new HttpParams().set('name', name)
    });
  }

  // Filter by category
  getProductsByCategoryId(id: number): Observable<ProductResponse[]> {
    return this.http.get<ProductResponse[]>(`${this.apiUrl}/category/${id}`);
  }

  getProductsByCategoryName(name: string): Observable<ProductResponse[]> {
    return this.http.get<ProductResponse[]>(`${this.apiUrl}/category/search`, {
      params: new HttpParams().set('name', name)
    });
  }

  // Filter by price range
  filterByPrice(name: string, min: number, max: number): Observable<ProductResponse[]> {
    return this.http.get<ProductResponse[]>(`${this.apiUrl}/search/price`, {
      params: new HttpParams()
        .set('name', name)
        .set('min', min.toString())
        .set('max', max.toString())
    });
  }

  // Get available products (with pagination)
  getAvailableProducts(page: number = 0, size: number = 10, sort: string = 'name'): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/available`, {
      params: new HttpParams()
        .set('page', page.toString())
        .set('size', size.toString())
        .set('sort', sort)
    });
  }

  // Create product with images
  createProductWithImages(product: ProductRequest, files: File[]): Observable<ProductResponse> {
    const formData = new FormData();
    formData.append('product', new Blob([JSON.stringify(product)], { type: 'application/json' }));
    files.forEach(file => formData.append('files', file));
    return this.http.post<ProductResponse>(`${this.apiUrl}/createProductWithImage`, formData);
  }

  // Update product with images
  updateProductWithImages(id: number, product: ProductRequest, files: File[]): Observable<ProductResponse> {
    const formData = new FormData();
    formData.append('product', new Blob([JSON.stringify(product)], { type: 'application/json' }));
    files.forEach(file => formData.append('files', file));
    return this.http.put<ProductResponse>(`${this.apiUrl}/updateProductWithImage/${id}`, formData);
  }

  // Delete single image
  deleteSingleImage(id: number, file: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/deleteSingleImage/${id}`, {
      params: new HttpParams().set('file', file)
    });
  }

  // Delete product with images
  deleteProductWithImages(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/deleteProductWithImage/${id}`);
  }

}
