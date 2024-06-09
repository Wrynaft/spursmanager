import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, switchMap, throwError } from 'rxjs';
import { Player } from './player';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getPlayers(): Observable<Player[]>{
    return this.http.get<Player[]>(`${this.apiServerUrl}/players/all`);
  }

  public addPlayers(player: Player): Observable<Player>{
    return this.http.post<Player>(`${this.apiServerUrl}/players/add`, player);
  }

  public updatePlayers(player: Player): Observable<Player>{
    return this.http.put<Player>(`${this.apiServerUrl}/players/update`, player);
  }

  public deletePlayers(playerId: number): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/players/delete/${playerId}`);
  }

  public getSize(): Observable<number>{
    return this.http.get<number>(`${this.apiServerUrl}/players/size`);
  }

  public getSalary(): Observable<number>{
    return this.http.get<number>(`${this.apiServerUrl}/players/salary`);
  }

  public validateRoster(): Observable<string>{
    return this.http.get<string>(`${this.apiServerUrl}/players/validate`);
  }

  public searchPlayers(searchParams: Map<string, string>): Observable<Player[]>{
    return this.http.post<Player[]>(`${this.apiServerUrl}/players/search`, searchParams);
  }

  public clearRoster(): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/players/clear`);
  }

  public getRanking(): Observable<Player[]>{
    return this.http.get<Player[]>(`${this.apiServerUrl}/players/ranking`);
  }
}
