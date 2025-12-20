import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DealCreate, DealResponse, DealUpdate } from '../model/deal.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DealService {

  private apiUrl = 'http://localhost:8080/api/deals';

  constructor(private http: HttpClient) {}

  // Get all deals
  getAllDeals(): Observable<DealResponse[]> {
    return this.http.get<DealResponse[]>(this.apiUrl);
  }

  // Get deal by ID
  getDealById(id: number): Observable<DealResponse> {
    return this.http.get<DealResponse>(`${this.apiUrl}/${id}`);
  }

  // Create new deal
  createDeal(deal: DealCreate): Observable<DealResponse> {
    return this.http.post<DealResponse>(this.apiUrl, deal);
  }

  // Update existing deal
  updateDeal(id: number, deal: DealUpdate): Observable<DealResponse> {
    return this.http.put<DealResponse>(`${this.apiUrl}/${id}`, deal);
  }

  // Delete deal
  deleteDeal(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
