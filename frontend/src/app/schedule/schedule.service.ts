import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Schedule } from './schedule';

@Injectable({
  providedIn: 'root'
})
export class ScheduleService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  public getSchedule(): Observable<Schedule[]>{
    return this.http.get<Schedule[]>(`${this.apiServerUrl}/schedule/shortestPath`);
  }

  public getDistance(): Observable<number>{
    return this.http.get<number>(`${this.apiServerUrl}/schedule/shortestDistance`);
  }
}
