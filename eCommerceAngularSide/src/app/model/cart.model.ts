
export interface CartItemDto {
  id: number;
  productId: number;
  productName: string;
  quantity: number;
  price: number;
  totalPrice: number;
}

export interface CartDto {
  id: number;
  userId: number;
  items: CartItemDto[];
  totalPrice: number;
}

export interface Cart {
    id?: number;
    userId: number | null;
    items: CartItemDto[];
}

