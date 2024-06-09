import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Player } from '../player/player';
import { FreeagentService } from './freeagent.service';
import { HttpClientModule, HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { PlayerService } from '../player/player.service';
import { FormsModule, NgForm } from '@angular/forms';
import { searchAgent } from './searchAgent';

@Component({
  selector: 'app-freeagent',
  standalone: true,
  imports: [HttpClientModule, CommonModule, FormsModule],
  templateUrl: './freeagent.component.html',
  styleUrl: './freeagent.component.css'
})
export class FreeagentComponent implements OnInit{
  public freeagents: Player[];
  public searchedagents: Player[];
  public addingPlayer!: Player;
  public size!: number;
  public salary?: number;

  constructor(private freeAgentService: FreeagentService, private playerService: PlayerService) {
    this.freeagents = [];
    this.searchedagents = [];
  }

  ngOnInit(){
    this.getAllPlayers();
    this.getSize();
    this.getSalary();
  }


  public getAllPlayers():void{
    this.freeAgentService.getAllPlayer().subscribe(
      {
        next: (response: Player[])=>{
          this.freeagents = response;
          this.searchedagents = this.freeagents;
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

  public tryAddPlayer(player: Player): void{
    this.addingPlayer = player;
  }

  public onAddPlayer(): void{
    this.playerService.addPlayers(this.addingPlayer).subscribe(
      {
        next: (response: Player) => {
          console.log(response);
          alert("Player successfully added!")
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

  public onSearchAgent(searchForm: NgForm): void{
    let searchAgent = {
      searchParams: searchForm.value,
      agentList: this.freeagents
    }
    console.log(searchAgent)
    this.freeAgentService.searchAgents(searchAgent).subscribe(
      {
        next: (response: Player[])=>{
          this.searchedagents = response;
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error.message);
        }
      }
    );
  }
}
