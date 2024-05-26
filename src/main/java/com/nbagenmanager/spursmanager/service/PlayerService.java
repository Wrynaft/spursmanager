/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager.service;

import com.nbagenmanager.spursmanager.model.Player;
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
@Service
public class PlayerService {
    private final PlayerRepo playerRepo;

    @Autowired
    public PlayerService(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }
    
    public Player addPlayer(Player player){
        if (playerRepo.findAll().size()>=15){
            throw new TeamSizeException("Adding this player will exceed the maximum team size!");
        }
        double currentSalary=0;
        for (Player players : playerRepo.findAll()){
            currentSalary += players.getSalary();
        }
        if (currentSalary + player.getSalary() > 20000){
            throw new ExceedSalaryException("Adding this player will exceed the salary cap!");
        }
        return playerRepo.save(player);
    }
    
    public List<Player> findAllPlayers(){
        return playerRepo.findAll();
    }
    
    public boolean validateRoster(){
        List<Player> roster = playerRepo.findAll();
        if (roster.size()>15 || roster.size()<10)
            throw new TeamSizeException("Team size does not fulfill the requirement.");
        double totalSalary = 0;
        for (Player players : roster){
            totalSalary += players.getSalary();
        }
        if (totalSalary > 20000)
            throw new ExceedSalaryException("The salary cap has been exceeded.");
        if (!validatePosition(roster))
            throw new PositionalRequirementException("The positional requirements are not fulfilled.");
        return true;
    }
    
    public boolean validatePosition(List<Player> roster){
        List<Player> guard = new ArrayList<>();
        List<Player> forward = new ArrayList<>();
        List<Player> center = new ArrayList<>();
        List<Player> hybrid = new ArrayList<>();
        
        for (Player players : roster){
            if (players.getPosition().length()>1)
                hybrid.add(players);
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
        
        for (Player hPlayer : hybrid){
            String firstPos = Character.toString(hPlayer.getPosition().charAt(0));
            String secondPos = Character.toString(hPlayer.getPosition().charAt(2));
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
        
        if (forward.size()<2 || center.size()<2 || guard.size()<2)
            return false;
        else
            return true;
    }
    
    public Player updatePlayer(Player player){
        return playerRepo.save(player);
    }
    
    public Player findPlayerById(Long id){
        return playerRepo.findPlayerById(id).orElseThrow(()-> new PlayerNotFoundException("User was not found"));
    }
    
    @Transactional
    public void deletePlayer(Long id){
        List<Player> roster = playerRepo.findAll();
        if (roster.size()<=10){
            throw new TeamSizeException("Removing the player will cause the team to have insufficient members.");
        }
        Player removed = findPlayerById(id);
        roster.remove(removed);
        if (!validatePosition(roster))
            throw new PositionalRequirementException("Removing the player will disobey the positional requirement.");
        playerRepo.deletePlayerById(id);
    }
    
    public Integer getSize(){
        return playerRepo.findAll().size();
    }
    
    public double getSalary(){
        double totalSalary = 0;
        for (Player players : playerRepo.findAll()){
            totalSalary += players.getSalary();
        }
        return totalSalary;
    }
    
    //John please do your part here
    public List<Player> searchPlayers(Map<String, String> searchParams){
        List<Player> roster = playerRepo.findAll();
        return null;
    }
}
