import { CommonModule } from '@angular/common';
import { HttpClientModule, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Player } from '../player/player';
import { PlayerService } from '../player/player.service';

@Component({
  selector: 'app-ranking',
  standalone: true,
  imports: [HttpClientModule, CommonModule],
  templateUrl: './ranking.component.html',
  styleUrl: './ranking.component.css'
})
export class RankingComponent implements OnInit{
  public players: Player[];
  
  constructor(private playerService: PlayerService) {
    this.players = [];
  }

  ngOnInit(): void {
    this.getRanking();
  }

  public getRanking(): void{
    this.playerService.getRanking().subscribe(
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

}
