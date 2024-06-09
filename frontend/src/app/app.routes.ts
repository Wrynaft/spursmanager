import { Routes } from '@angular/router';
import { PlayerComponent } from './player/player.component';
import { DefaultComponent } from './default/default.component';
import { FreeagentComponent } from './freeagent/freeagent.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { InjuryComponent } from './injury/injury.component';
import { ContractComponent } from './contract/contract.component';
import { RankingComponent } from './ranking/ranking.component';

export const routes: Routes = [
    {path: '', component: DefaultComponent},
    {path: 'players', component: PlayerComponent},
    {path: 'freeagent', component: FreeagentComponent},
    {path: 'schedule', component: ScheduleComponent},
    {path: 'injury', component: InjuryComponent},
    {path: 'contract', component: ContractComponent},
    {path: 'ranking', component: RankingComponent}
];
