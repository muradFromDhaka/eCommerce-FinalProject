import { Order } from './order.model';
import { Product } from './product.model';

export interface OrderItem {
    id?: number;           // BaseEntity থেকে
    order?: Order;         // optional relation
    product?: Product;     // optional relation
    quantity: number;
    unit_price: number;    // BigDecimal → number

    createdAt?: string;    // BaseEntity timestamp
    updatedAt?: string;
}
