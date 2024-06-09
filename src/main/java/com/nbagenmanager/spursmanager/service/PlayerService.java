/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager.service;

import com.nbagenmanager.spursmanager.model.Player;
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
    private final FilterPlayerService filterPlayerService;

    @Autowired
    public PlayerService(PlayerRepo playerRepo, InjuryReserveRepo injuryReserveRepo, FilterPlayerService filterPlayerService) {
        this.playerRepo = playerRepo;
        this.injuryReserveRepo = injuryReserveRepo;
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
        injuryReserveRepo.deleteInjuryReserveById(id);
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
        List<Player> roster = playerRepo.findAll();
        List<Player> filteredPlayers = new ArrayList<>();

        // Loop through the search parameters, and filter by each parameter
        for (Map.Entry<String, String> entry : searchParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            // Assuming the search parameter format is "attribute:comparator:value"
            String[] parts = value.split(":");
            if (parts.length != 2) {
                throw new InvalidSearchParameterException("Invalid search parameter format for attribute: " + key);
            }   

            String attribute = key;
            String comparator = parts[0];
            Object searchValue = convertToType(parts[1], attribute);
            if (searchValue == null) {
                throw new InvalidSearchParameterException("Invalid search parameter value for attribute: " + attribute);
            }

            filteredPlayers = filterPlayerService.filterPlayers(roster, attribute, comparator, searchValue);
            roster = filteredPlayers;
        }

        // Update the filtered rosters for next loop
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
}
