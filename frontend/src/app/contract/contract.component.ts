import { Component, OnInit } from '@angular/core';
import { ContractService } from './contract.service';
import { Contract } from './contract';
import { HttpClientModule, HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-contract',
  standalone: true,
  imports: [HttpClientModule, CommonModule],
  templateUrl: './contract.component.html',
  styleUrl: './contract.component.css'
})
export class ContractComponent implements OnInit{
  public contracts: Contract[];

  constructor(private contractService: ContractService) {
    this.contracts = [];
   }

   ngOnInit(){
    this.getContracts();
   }

   public getContracts(): void{
    this.contractService.getContracts().subscribe(
      {
        next: (response: Contract[])=>{
          this.contracts = response;
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error.message);
        }
      }
      )
    }

    public dequeueContract(): void{
      this.contractService.dequeueContract().subscribe(
        {
          next: (response: Contract)=>{
            alert(`Player ${response.name}'s contract has been renewed`)
            this.getContracts();
          },
          error: (error: HttpErrorResponse) => {
            alert(error.error.message);
          }
        }
        )
    }
}
