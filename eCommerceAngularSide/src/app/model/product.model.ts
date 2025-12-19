export interface Product {
  id: number;
  name: string;
  description?: string;
  price: number;
  stockQuantity?: number;
  categoryId: number;
  available?: boolean;
  releaseDate?: string;
  imageUrls?: string[];

  // ⭐ rating info (READ ONLY)
  averageRating?: number;
  totalReviews?: number;
}

export interface ProductRequest {
  name: string;
  description?: string;
  price: number;
  stockQuantity?: number;
  categoryId: number;
  available?: boolean;
  releaseDate?: string;
  imageUrls?: string[];
}

export interface ProductListDto {
  id: number;
  name: string;
  description?: string;
  price: number;
  available?: boolean;
  categoryId?: number;
  stockQuantity?: number;
  thumbnailUrl?: string;

}

export interface ProductDetailsDto {
  id: number;
  name: string;
  description?: string;
  price: number;
  stockQuantity?: number;
  available?: boolean;
  categoryName?: string;
  releaseDate?: string;
  imageUrls?: string[];

  averageRating?: number;
  totalReviews?: number;
}

// src/app/model/page.model.ts
export interface Page<T> {
  content: T[]; // actual list of products
  totalElements: number;
  totalPages: number;
  number: number; // current page number
  size: number; // page size
  first: boolean;
  last: boolean;
  numberOfElements: number;
}
