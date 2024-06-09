import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Player } from '../player/player';
import { searchAgent } from './searchAgent';

@Injectable({
  providedIn: 'root'
})
export class FreeagentService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getAllPlayer(): Observable<Player[]>{
    return this.http.get<Player[]>(`${this.apiServerUrl}/freeagents`);
  }

  public searchAgents(searchObject: searchAgent): Observable<Player[]>{
    return this.http.post<Player[]>(`${this.apiServerUrl}/freeagents/search`, searchObject);
  }
}
