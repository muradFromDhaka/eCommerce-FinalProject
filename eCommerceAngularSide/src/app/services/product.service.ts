import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Page, Product, ProductDetailsDto, ProductListDto, ProductRequest } from '../model/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiUrl = 'http://localhost:8080/api/products';

  constructor(private http: HttpClient) {}

  //-----------------------------
  // BASIC CRUD
  //-----------------------------

  getAll(): Observable<ProductListDto[]> {
    return this.http.get<ProductListDto[]>(this.apiUrl);
  }

  getById(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.apiUrl}/${id}`);
  }

  create(product: ProductRequest): Observable<ProductRequest> {
    return this.http.post<ProductRequest>(this.apiUrl, product);
  }

  update(id: number, product: ProductRequest): Observable<ProductRequest> {
    return this.http.put<ProductRequest>(`${this.apiUrl}/update/${id}`, product);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/deleteProductWithImage/${id}`);
  }

  //-----------------------------
  // CATEGORY
  //-----------------------------

  getProductsByCategory(categoryId: number): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiUrl}/category/${categoryId}`);
  }

  searchProductsByCategoryName(name: string): Observable<Product[]> {
    const params = new HttpParams().set('name', name);
    return this.http.get<Product[]>(`${this.apiUrl}/category/search`, { params });
  }

  //-----------------------------
  // SEARCH
  //-----------------------------

  searchProductsByName(name: string): Observable<Product[]> {
    const params = new HttpParams().set('name', name);
    return this.http.get<Product[]>(`${this.apiUrl}/searchProducts`, { params });
  }

  //-----------------------------
  // PRICE FILTER
  //-----------------------------

  filterByPrice(min: number, max: number, name?: string): Observable<Product[]> {
    let params = new HttpParams()
      .set('min', min)
      .set('max', max);

    if (name) params = params.set('name', name);

    return this.http.get<Product[]>(`${this.apiUrl}/search/price`, { params });
  }

  //-----------------------------
  // AVAILABLE (pagination)
  //-----------------------------

  getAvailableProducts(
    page: number = 0, 
    size: number = 10, 
    sort: string = 'name'
  ): Observable<Page<Product>> {

    const params = new HttpParams()
      .set('page', page)
      .set('size', size)
      .set('sort', sort);

    return this.http.get<Page<Product>>(`${this.apiUrl}/available`, { params });
  }

  //-----------------------------
  // IMAGE UPLOAD + UPDATE
  //-----------------------------

  // CREATE PRODUCT + IMAGES
  createProductWithImages(product: Product, files: File[]): Observable<Product> {
    const formData = new FormData();
    formData.append("product", new Blob([JSON.stringify(product)], { type: "application/json" }));

    files.forEach(file => formData.append("files", file));

    return this.http.post<Product>(`${this.apiUrl}/createProductWithImage`, formData);
  }

  // UPDATE PRODUCT + IMAGES
  updateProductWithImages(id: number, product: Product, files: File[]): Observable<Product> {
    const formData = new FormData();
    formData.append("product", new Blob([JSON.stringify(product)], { type: "application/json" }));

    files.forEach(file => formData.append("files", file));

    return this.http.put<Product>(`${this.apiUrl}/updateProductWithImage/${id}`, formData);
  }

  //-----------------------------
  // DELETE SINGLE IMAGE
  //-----------------------------

 deleteSingleImage(id: number, imageUrl: string) {
  return this.http.delete(`${this.apiUrl}/deleteSingleImage/${id}`, {
    params: { file: imageUrl }
  });
}


}
