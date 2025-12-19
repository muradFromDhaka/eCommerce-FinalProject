import { OrderItem } from './orderItem.model';

export type OrderStatus = 'PENDING' | 'PAID' | 'SHIPPED' | 'DELIVERED' | 'CANCELLED';

export interface Order {
    id?: number;                 // BaseEntity থেকে
    orderItems?: OrderItem[];    // optional
    totalPrice: number;          // BigDecimal → number
    status: OrderStatus;

    createdAt?: string;          // BaseEntity timestamp
    updatedAt?: string;
}
