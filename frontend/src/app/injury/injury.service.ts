import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { InjuryReserve } from './injuryReserve';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class InjuryService {
  private apiServerUrl = environment.apiBaseUrl;
  
  constructor(private http: HttpClient) { }

  public getInjuryReserve(): Observable<InjuryReserve[]>{
    return this.http.get<InjuryReserve[]>(`${this.apiServerUrl}/injury/all`);
  }

  public popInjuryReserve(): Observable<InjuryReserve>{
    return this.http.get<InjuryReserve>(`${this.apiServerUrl}/injury/pop`);
  }

  public pushInjured(injured: InjuryReserve): Observable<InjuryReserve>{
    return this.http.post<InjuryReserve>(`${this.apiServerUrl}/injury/push`, injured);
  }
}
