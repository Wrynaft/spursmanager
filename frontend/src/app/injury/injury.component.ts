import { Component, OnInit } from '@angular/core';
import { InjuryReserve } from './injuryReserve';
import { InjuryService } from './injury.service';
import { HttpClientModule, HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-injury',
  standalone: true,
  imports: [HttpClientModule, CommonModule],
  templateUrl: './injury.component.html',
  styleUrl: './injury.component.css'
})
export class InjuryComponent implements OnInit{
  public injuryReserve: InjuryReserve[];

  constructor(private injuryReserveService: InjuryService) {
    this.injuryReserve = [];
   }

   ngOnInit(){
    this.getInjured();
   }

   public getInjured(): void{
    this.injuryReserveService.getInjuryReserve().subscribe(
      {
        next: (response: InjuryReserve[])=>{
          this.injuryReserve = response;
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error.message);
        }
      }
      )
    }

    public popInjured(): void{
      this.injuryReserveService.popInjuryReserve().subscribe(
        {
          next: (response: InjuryReserve)=>{
            alert(`Player ${response.name} has returned to the active roster.`)
            this.getInjured();
          },
          error: (error: HttpErrorResponse) => {
            alert(error.error.message);
          }
        }
        )
    }
}
