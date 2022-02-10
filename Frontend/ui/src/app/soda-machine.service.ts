import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASE_URL = 'http://localhost:8080';

@Injectable({
  providedIn: 'root',
})
export class SodaMachineService {
  constructor(private http: HttpClient) {}

  getRemainingSoda(): Observable<any> {
    return this.http.get(`${BASE_URL}/soda/status`);
  }
  insert(): Observable<any> {
    return this.http.get(`${BASE_URL}/coin/insert`);
  }
  cancel(): Observable<any> {
    return this.http.get(`${BASE_URL}/coin/remove`);
  }
  withdraw(): Observable<any> {
    return this.http.post(`${BASE_URL}/soda/despence`, {});
  }
}
