// import { Order } from './order.model';
// import { Review } from './review.model';
// import { Wishlist } from './wishlist.model';

// export type UserRole = 'USER' | 'ADMIN' | 'EMPLOYEE';

// export interface User {
//     id?: number;                // BaseEntity থেকে
//     name: string;
//     email: string;
//     password?: string;           // optional for security
//     phone?: string;
//     role: UserRole;
//     active: boolean;
//     address?: string;
//     profilePicture?: string;

//     orders?: Order[];           // optional relation
//     reviews?: Review[];         // optional relation
//     wishlists?: Wishlist[];     // optional relation

//     createdAt?: string;         // BaseEntity থেকে
//     updatedAt?: string;         // BaseEntity থেকে
// }


export interface UserBasic {
  id: number;
  name: string;
  email: string;
  profilePicture?: string;
}

export interface UserProfile extends UserBasic {
  phone?: string;
  address?: string;
  active?: boolean;
  role?: string;
}

export interface UserCreate {
  name: string;
  email: string;
  password: string;
}

export interface UserUpdate {
  name?: string;
  phone?: string;
  address?: string;
  profilePicture?: string;
}
