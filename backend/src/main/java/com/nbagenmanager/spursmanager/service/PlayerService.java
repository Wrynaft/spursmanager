/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager.service;

import com.nbagenmanager.spursmanager.model.Player;
import com.nbagenmanager.spursmanager.repo.ContractRepo;
import com.nbagenmanager.spursmanager.repo.InjuryReserveRepo;
import com.nbagenmanager.spursmanager.repo.PlayerRepo;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryan Chin
 */

//Player service class to handle operations related to Player
@Service
public class PlayerService {
    private final PlayerRepo playerRepo;
    private final InjuryReserveRepo injuryReserveRepo;
    private final ContractRepo contractRepo;
    private final FilterPlayerService filterPlayerService;

    @Autowired
    public PlayerService(PlayerRepo playerRepo, InjuryReserveRepo injuryReserveRepo, ContractRepo contractRepo, FilterPlayerService filterPlayerService) {
        this.playerRepo = playerRepo;
        this.injuryReserveRepo = injuryReserveRepo;
        this.contractRepo = contractRepo;
        this.filterPlayerService = filterPlayerService;
    }
    
    //Function to add player
    public Player addPlayer(Player player){
        //Before adding, we need to check if adding the player will break any rules
        
        //Check if adding the player will exceed team size or not
        if (playerRepo.findAll().size()>=15){
            throw new TeamSizeException("Adding this player will exceed the maximum team size!");
        }
        //Check if adding the player will exceed salary cap or not
        double currentSalary=0;
        for (Player players : playerRepo.findAll()){
            currentSalary += players.getSalary();
        }
        if (currentSalary + player.getSalary() > 20000){
            throw new ExceedSalaryException("Adding this player will exceed the salary cap!");
        }
        
        //If no problems, then add the player into database
        return playerRepo.save(player);
    }
    
    //Function to get all players in the roster from database
    public List<Player> findAllPlayers(){
        return playerRepo.findAll();
    }
    
    //Function to check if the roster currently
    public boolean validateRoster(){
        //Get list of players from roster database
        List<Player> roster = playerRepo.findAll();
        //Check team size
        if (roster.size()>15 || roster.size()<10)
            throw new TeamSizeException("Team size does not fulfill the requirement.");
        //Check total salary
        double totalSalary = 0;
        for (Player players : roster){
            totalSalary += players.getSalary();
        }
        if (totalSalary > 20000)
            throw new ExceedSalaryException("The salary cap has been exceeded.");
        //Check positional requirement
        if (!validatePosition(roster))
            throw new PositionalRequirementException("The positional requirements are not fulfilled.");
        return true;
    }
    
    //Function to check positional requirement
    public boolean validatePosition(List<Player> roster){
        //Create lists to sort players into their positions
        List<Player> guard = new ArrayList<>();
        List<Player> forward = new ArrayList<>();
        List<Player> center = new ArrayList<>();
        List<Player> hybrid = new ArrayList<>();
        
        for (Player players : roster){
            //Put hybrid position players in a list first
            if (players.getPosition().length()>1)
                hybrid.add(players);
            //Sort the players with a single position first
            else
                switch(players.getPosition()){
                    case ("F"):
                        forward.add(players);
                        break;
                    case ("G"):
                        guard.add(players);
                        break;
                    case ("C"):
                        center.add(players);
                        break;
                }
        }
        
        //Sort the hybrid position players now by inserting them into positions that are insufficient
        for (Player hPlayer : hybrid){
            String firstPos = Character.toString(hPlayer.getPosition().charAt(0));
            String secondPos = Character.toString(hPlayer.getPosition().charAt(2));
            //Check if there is any positions that are still insufficient for the hybrid's first position, and insert them there if so
            switch (firstPos){
                case ("F"):
                    if (forward.size()<2){
                        forward.add(hPlayer);
                        continue;
                    }
                    break;
                case ("G"):
                    if (guard.size()<2){
                        guard.add(hPlayer);
                        continue;
                    }
                    break;
                case ("C"):
                    if (center.size()<2){
                        center.add(hPlayer);
                        continue;
                    }
                    break;
            }
            //If none, then just insert the player in its second position
            switch(secondPos){
                case ("F"):
                    forward.add(hPlayer);
                    break;
                case ("G"):
                    guard.add(hPlayer);
                    break;
                case ("C"):
                    center.add(hPlayer);
                    break;
            }
        }
        
        //Check if all positions have sufficient members
        if (forward.size()<2 || center.size()<2 || guard.size()<2)
            return false;
        else
            return true;
    }
    
    //Update a player detail
    public Player updatePlayer(Player player){
        return playerRepo.save(player);
    }
    
    //Find a player by Player ID
    public Player findPlayerById(Long id){
        return playerRepo.findPlayerById(id).orElseThrow(()-> new PlayerNotFoundException("User was not found"));
    }
    
    //Delete a player by PlayerID
    @Transactional
    public void deletePlayer(Long id){
        //Get list of players from roster database
        List<Player> roster = playerRepo.findAll();
        //Check if removing the player will cause team size to be too small, and disallow it if so
        if (roster.size()<=10){
            throw new TeamSizeException("Removing the player will cause the team to have insufficient members.");
        }
        Player removed = findPlayerById(id);
        roster.remove(removed);
        //Check if removing the player will disobey the positional requirements
        if (!validatePosition(roster))
            throw new PositionalRequirementException("Removing the player will disobey the positional requirement.");
        playerRepo.deletePlayerById(id);
        injuryReserveRepo.deleteInjuryReserveById(id); //Delete the player from injury reserve as well
        contractRepo.deleteContractById(id); //Delete the player from contract extension as well
    }
    
    //Get the size of the roster
    public Integer getSize(){
        return playerRepo.findAll().size();
    }
    
    //Get the total salary of the roster
    public double getSalary(){
        double totalSalary = 0;
        for (Player players : playerRepo.findAll()){
            totalSalary += players.getSalary();
        }
        return totalSalary;
    }
    
    //John's Dynamic Searching done!
    public List<Player> searchPlayers(Map<String, String> searchParams) {
        //The dynamic searching filters are passed in the form of a HashMap
        for (Map.Entry<String, String> entry : searchParams.entrySet()){
            if (entry.getValue()==null){
                entry.setValue("");
            }
        } //There may null value passed from empty fields in front end. Replace them with empty strings for less complications
        List<Player> roster = playerRepo.findAll(); //Get the list of players in rosters
        List<Player> filteredPlayers = new ArrayList<>(); //This list will hold the players that fit the filters
        filteredPlayers = roster; //Initialise the list with the full roster list
        
        //Check the hashmap one by one. Only filter the list if relevant attributes are not empty
        //Refer to filterPlayerService for individual attribute filtering logic
        if (!searchParams.get("name").isEmpty()){
            filteredPlayers = filterPlayerService.filterByName(roster, searchParams.get("name"));
            roster = filteredPlayers; //Reduce the roster to the filtered list. For future filters, we will filter from this filtered list
        }
        if (!searchParams.get("ageFilter").isEmpty() && !searchParams.get("age").isEmpty()){
            System.out.println("Age invoked");
            filteredPlayers = filterPlayerService.filterByAge(roster, searchParams.get("ageFilter"), Integer.parseInt(searchParams.get("age")));
            roster = filteredPlayers;
        }
        if (!searchParams.get("heightFilter").isEmpty() && !searchParams.get("height").isEmpty()){
            filteredPlayers = filterPlayerService.filterByHeight(roster, searchParams.get("heightFilter"), Double.parseDouble(searchParams.get("height")));
            roster = filteredPlayers;
        }
        if (!searchParams.get("weightFilter").isEmpty() && !searchParams.get("weight").isEmpty()){
            filteredPlayers = filterPlayerService.filterByWeight(roster, searchParams.get("weightFilter"), Double.parseDouble(searchParams.get("weight")));
            roster = filteredPlayers;
        }
        if (!searchParams.get("salaryFilter").isEmpty() && !searchParams.get("salary").isEmpty()){
            filteredPlayers = filterPlayerService.filterBySalary(roster, searchParams.get("salaryFilter"), Double.parseDouble(searchParams.get("salary")));
            roster = filteredPlayers;
        }
        if (!searchParams.get("posFilter").isEmpty()){
            filteredPlayers = filterPlayerService.filterByPosition(roster, searchParams.get("posFilter"));
            roster = filteredPlayers;
        }
        if (!searchParams.get("pointsFilter").isEmpty() && !searchParams.get("points").isEmpty()){
            filteredPlayers = filterPlayerService.filterByPoints(roster, searchParams.get("pointsFilter"), Double.parseDouble(searchParams.get("points")));
            roster = filteredPlayers;
        }
        if (!searchParams.get("rbFilter").isEmpty() && !searchParams.get("rebounds").isEmpty()){
            filteredPlayers = filterPlayerService.filterByRebound(roster, searchParams.get("rbFilter"), Double.parseDouble(searchParams.get("rebounds")));
            roster = filteredPlayers;
        }if (!searchParams.get("astFilter").isEmpty() && !searchParams.get("assists").isEmpty()){
            filteredPlayers = filterPlayerService.filterByAssists(roster, searchParams.get("astFilter"), Double.parseDouble(searchParams.get("assists")));
            roster = filteredPlayers;
        }
        if (!searchParams.get("stlFilter").isEmpty() && !searchParams.get("steals").isEmpty()){
            filteredPlayers = filterPlayerService.filterBySteals(roster, searchParams.get("stlFilter"), Double.parseDouble(searchParams.get("steals")));
            roster = filteredPlayers;         
        }
        if (!searchParams.get("blkFilter").isEmpty() && !searchParams.get("blocks").isEmpty()){
            filteredPlayers = filterPlayerService.filterByBlocks(roster, searchParams.get("blkFilter"), Double.parseDouble(searchParams.get("blocks")));
            roster = filteredPlayers;
        }
        //The filtered list will keep on updating with each attribute filters, until here where we return the final filtered list.
        return filteredPlayers;
    }

    private Object convertToType(String value, String attribute) {
        switch (attribute) {
            case "height":
            case "weight":
                return Double.parseDouble(value);
            case "age":
                return Integer.parseInt(value);
            case "position":
                return value;
            // Add more cases for other attributes as needed
            default:
                return null;
        }
    }
    
    //Delete all players at all databases to clear the roster
    public void clearRoster(){
        playerRepo.deleteAll();
        injuryReserveRepo.deleteAll();
        contractRepo.deleteAll();
    }
}
