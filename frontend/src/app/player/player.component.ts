import { Component, OnInit, ViewChild } from '@angular/core';
import { Player } from './player';
import { PlayerService } from './player.service';
import { HttpClientModule, HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { InjuryReserve } from '../injury/injuryReserve';
import { InjuryService } from '../injury/injury.service';
import { ContractService } from '../contract/contract.service';
import { Contract } from '../contract/contract';

@Component({
  selector: 'app-player',
  standalone: true,
  imports: [HttpClientModule, CommonModule, FormsModule],
  templateUrl: './player.component.html',
  styleUrl: './player.component.css'
})
export class PlayerComponent implements OnInit{
  public players: Player[];
  public size!: number;
  public salary?: number;
  public deletePlayer: number = 0;
  public injured!: InjuryReserve;
  public injuredList: number[] = [];
  public contract!: Contract;
  public contractList: number[] = [];
  public validation!: boolean;

  constructor(private playerService: PlayerService, private injuryService: InjuryService, private contractService: ContractService) {
    this.players = [];
   }

  ngOnInit(){
    this.getPlayers();
    this.getSize();
    this.getSalary();
    this.getInjured();
    this.getContracts();
    this.validateRoster();
  }

  public validateRoster(): void{
    this.playerService.validateRoster().subscribe(
      {
        next: (response: string)=>{
          this.validation = true;
        },
        error: (error: HttpErrorResponse) => {
          this.validation = false;
        }
      }
    )
  }

  public getPlayers(): void{
    this.playerService.getPlayers().subscribe(
      {
        next: (response: Player[])=>{
          this.players = response;
        },
        error: (error: HttpErrorResponse) => {
          alert(error.message);
        }
      }
      )
  }

  public getSize(): void{
    this.playerService.getSize().subscribe(
      {
        next: (response: number)=>{
          this.size = response;
        },
        error: (error: HttpErrorResponse) => {
          alert(error.message);
        }
      }
    )
  }

  public getSalary(): void{
    this.playerService.getSalary().subscribe(
      {
        next: (response: number)=>{
          this.salary = response;
        },
        error: (error: HttpErrorResponse) => {
          alert(error.message);
        }
      }
    )
  }

  public tryDeletePlayer(playerId: number): void{
    this.deletePlayer = playerId;
  }

  public setInjured(playerId: number, playerName: string){
    this.injured = {
      id: playerId,
      name: playerName,
      seq: -1
    }
  }

  public setContract(playerId: number, playerName: string, point: number){
    this.contract= {
      id: playerId,
      name: playerName,
      points: point,
      seq: -1
    }
  }

  public onEnqueueContract(): void{
    this.contractService.enqueueContract(this.contract).subscribe(
      {
        next: (response: InjuryReserve) => {
          console.log(response);
          alert("Player added to contract extension queue");
          this.getPlayers();
          this.getContracts();
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error.message);
        }
      }
    )
    document.getElementById("closeContractButton")?.click();
  }

  public onAddInjury(): void{
    this.injuryService.pushInjured(this.injured).subscribe(
      {
        next: (response: InjuryReserve) => {
          console.log(response);
          alert("Player added to injury reserve.");
          this.getPlayers();
          this.getInjured();
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error.message);
        }
      }
    )
    document.getElementById("closeInjuryButton")?.click();
  }

  public onDeletePlayer(): void{
    this.playerService.deletePlayers(this.deletePlayer).subscribe(
      {
        next: (response: void) => {
          console.log(response);
          alert("Successfully removed player");
          this.getPlayers();
          this.getSalary();
          this.getSize();
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error.message);
        }
      }
    )
    document.getElementById("closeModalButton")?.click();
  }

  public onSearchPlayer(searchForm: NgForm): void{
    this.playerService.searchPlayers(searchForm.value).subscribe(
      {
        next: (response: Player[])=>{
          this.players = response;
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error.message);
        }
      }
    );
    console.log(searchForm.value);
  }

  public getInjured(): void{
    this.injuryService.getInjuryReserve().subscribe(
      {
        next: (response: InjuryReserve[])=>{
          var tempList: number[] = [];
          response.forEach(function (value){
            tempList.push(value.id);
          })
          this.injuredList=tempList;
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error.message);
        }
      }
      )
    }

    public getContracts(): void{
      this.contractService.getContracts().subscribe(
        {
          next: (response: Contract[])=>{
            var tempList: number[] = [];
            response.forEach(function (value){
              tempList.push(value.id);
            })
            this.contractList=tempList;
          },
          error: (error: HttpErrorResponse) => {
            alert(error.error.message);
          }
        }
        )
      }
    
    public clearRoster(): void{
      this.playerService.clearRoster().subscribe(
        {
          next: (response: void) =>{
            alert("The roster has been cleared, time to build a new one!");
            this.getPlayers();
            this.getSalary();
            this.getSize();
          },
          error: (error: HttpErrorResponse) => {
            alert(error.error.message);
          }
        }
      )
    }
}
