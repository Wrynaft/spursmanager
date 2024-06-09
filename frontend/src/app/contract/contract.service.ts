import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { Contract } from './contract';

@Injectable({
  providedIn: 'root'
})
export class ContractService {
  private apiServerUrl = environment.apiBaseUrl;
  
  constructor(private http: HttpClient) { }

  public getContracts(): Observable<Contract[]>{
    return this.http.get<Contract[]>(`${this.apiServerUrl}/contract/all`);
  }

  public dequeueContract(): Observable<Contract>{
    return this.http.get<Contract>(`${this.apiServerUrl}/contract/dequeue`);
  }

  public enqueueContract(contract: Contract): Observable<Contract>{
    return this.http.post<Contract>(`${this.apiServerUrl}/contract/enqueue`, contract);
  }
}
