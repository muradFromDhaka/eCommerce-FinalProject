import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserCreate, UserProfile, UserUpdate } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
private api = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) {}

  register(data: UserCreate) {
    return this.http.post(`${this.api}/`, data);
  }

  getProfile() {
    return this.http.get<UserProfile>(`${this.api}`);
  }

  getProfileByid(id:number) {
    return this.http.get<UserProfile>(`${this.api}/${id}`);
  }

  updateProfile(data: UserUpdate) {
    return this.http.put(`${this.api}/update`, data);
  }


  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}
