import { Player } from "../player/player";

export interface searchAgent{
    searchParams: Map<string, string>; 
    agentList: Player[];
}