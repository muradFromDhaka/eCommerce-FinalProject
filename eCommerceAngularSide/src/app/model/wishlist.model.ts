import { Product } from './product.model';

export interface Wishlist {
    id?: number;           // BaseEntity থেকে
    products?: Product[];  // Set → array

    createdAt?: string;    // BaseEntity timestamp
    updatedAt?: string;
}
