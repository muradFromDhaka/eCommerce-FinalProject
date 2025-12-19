import { Product } from './product.model';

export interface Review {
    id?: number;            // BaseEntity থেকে
    rating: number;
    comment?: string;
    product?: Product;      // optional relation
    createdAt?: string;     // LocalDateTime → string
    updatedAt?: string;     // BaseEntity থেকে
}
