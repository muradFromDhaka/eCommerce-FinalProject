export interface DealResponse {
  id: number;
  title: string;
  discountPercent: number;
  startTime: string; // ISO string
  endTime: string;   // ISO string
  productId: number;
  productName: string;
}

export interface DealCreate {
  title: string;
  discountPercent: number;
  startTime: string; // ISO string
  endTime: string;   // ISO string
  productId: number;
}

export interface DealUpdate {
  title?: string;
  discountPercent?: number;
  startTime?: string;
  endTime?: string;
}
