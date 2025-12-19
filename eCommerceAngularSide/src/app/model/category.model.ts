import { Product } from './product.model';

// export interface Category {
//     id?: number;             // BaseEntity থেকে
//     name: string;
//     description?: string;
//     imageUrl?: string;
    
//     // products?: Product[];    // optional relation
//     // createdAt?: string;      // BaseEntity timestamp
//     // updatedAt?: string;
// }

export interface Category {
  id: number;
  name: string;
  description: string;
  imageUrl: string;
  createdAt?: Date;
  updatedAt?: Date;
}

export interface CategoryRequest {
  name: string;
  description: string;
  imageUrl: string;
}
