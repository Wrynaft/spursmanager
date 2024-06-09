import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Schedule } from './schedule';
import { ScheduleService } from './schedule.service';
import { HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Network, DataSet } from 'vis';

@Component({
  selector: 'app-schedule',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './schedule.component.html',
  styleUrl: './schedule.component.css'
})
export class ScheduleComponent implements OnInit{
  @ViewChild('network') el!: ElementRef<HTMLElement>;
  public schedule: Schedule[];
  public distance: number;
  private networkInstance: any;

  constructor(private scheduleService: ScheduleService) {
    this.schedule = [];
    this.distance = 0;
  }

  ngOnInit(): void {
    this.getSchedule();
    this.getDistance();
  }

  public generateGraph(): void {
    const nodes = new DataSet<any>([
       {id: "San Antonio Spurs", label: 'Spurs'},
       {id: "Phoenix Suns", label: 'Suns'},
       {id: "Oklahoma City Thunder", label: 'Thunder'},
       {id: "Houston Rockets", label: 'Rockets'},
       {id: "Orlando Magic", label: 'Magic'},
       {id: "Miami Heat", label: 'Heat'},
       {id: "Boston Celtics", label: 'Celtics'},
       {id: "Denver Nuggets", label: 'Nuggets'},
       {id: "Golden State Warriors", label: 'Warriors'},
       {id: "Los Angeles Lakers", label: 'Lakers'}
   ]);

   const edges = new DataSet<any>([
    {from: "San Antonio Spurs", to: "Phoenix Suns", weight: 500, label:"500km"},
    {from: "San Antonio Spurs", to: "Oklahoma City Thunder", weight: 678, label:"678km"},
    {from: "San Antonio Spurs", to: "Houston Rockets", weight: 983, label:"983km"},
    {from: "San Antonio Spurs", to: "Orlando Magic", weight: 1137, label:"1137km"},
    {from: "Phoenix Suns", to: "Los Angeles Lakers", weight: 577, label:"577km"},
    {from: "Oklahoma City Thunder", to: "Los Angeles Lakers", weight: 1901, label:"1901km"},
    {from: "Oklahoma City Thunder", to: "Golden State Warriors", weight: 2214, label:"2214km"},
    {from: "Oklahoma City Thunder", to: "Denver Nuggets", weight: 942, label:"942km"},
    {from: "Oklahoma City Thunder", to: "Houston Rockets", weight: 778, label:"778km"},
    {from: "Houston Rockets", to: "Boston Celtics", weight: 2584, label:"2584km"},
    {from: "Houston Rockets", to: "Orlando Magic", weight: 458, label:"458km"},
    {from: "Orlando Magic", to: "Miami Heat", weight: 268, label:"268km"},
    {from: "Miami Heat", to: "Boston Celtics", weight: 3045, label:"3045km"},
    {from: "Boston Celtics", to: "Denver Nuggets", weight: 2845, label:"2845km"},
    {from: "Denver Nuggets", to: "Golden State Warriors", weight: 1507, label:"1507km"},
    {from: "Golden State Warriors", to: "Los Angeles Lakers", weight: 554, label:"554km"},
  ]);


  for(let i=0; i<this.schedule.length-1; i++){
    var source = this.schedule[i].cityName;
    var destination = this.schedule[i+1].cityName;
    edges.forEach(function(edge){
      if((edge.from === source && edge.to === destination)||(edge.from == destination && edge.to == source)){
        edge.width = 5;
        edges.update(edge);
      }
    })
  }

  const data = { nodes, edges }; 

  var options = {
    width: "500px",
    height: "500px",
    interaction: {
      zoomView: false,
    },
    physics: {
      enabled: false,
    }
  };
  
  this.networkInstance = new Network(this.el.nativeElement, data, options);
  }

  public getSchedule(): void{
    this.scheduleService.getSchedule().subscribe(
      {
        next: (response: Schedule[])=>{
          this.schedule = response;
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error.message);
        }
      }
      )
  }

  public getDistance(): void{
    this.scheduleService.getDistance().subscribe(
      {
        next: (response: number) => {
          this.distance = response;
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error.message);
        }
      }
    )
  }
}
