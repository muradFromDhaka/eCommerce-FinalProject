export interface ProductResponse {
  id: number;
  name: string;
  description: string;
  price: number;
  stockQuantity: number;
  available: boolean;
  releaseDate: string; // ISO string
  categoryId?: number;
  categoryName?: string;
  vendorId?: number;
  vendorName?: string;
  averageRating?: number;
  totalReviews?: number;
  imageUrls?: string[];
}

export interface ProductList {
  id: number;
  name: string;
  price: number;
  available: boolean;
  thumbnailUrl?: string;
}

export interface ProductDetails extends ProductResponse {}

export interface ProductRequest {
  name: string;
  description: string;
  price: number;
  stockQuantity: number;
  available: boolean;
  releaseDate: string;
  categoryId?: number;
  imageUrls?: string[];
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
